package com.authine.cloudpivot.web.api.controller;

import com.authine.cloudpivot.engine.api.facade.BizObjectFacade;
import com.authine.cloudpivot.engine.api.model.runtime.BizObjectModel;
import com.authine.cloudpivot.engine.enums.ErrCode;
import com.authine.cloudpivot.web.api.bean.*;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.service.ExpertsDeclareService;
import com.authine.cloudpivot.web.api.utils.Points;
import com.authine.cloudpivot.web.api.utils.SequenceStatusUtils;
import com.authine.cloudpivot.web.api.utils.UserUtils;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @Author:lfh
 * @Date: 2020/1/13 14:01
 * @Description：专家申报控制层
 */
@RestController
@RequestMapping("/ext/expertsDeclare")
@Slf4j
public class ExpertsDeclareController extends BaseController {

    @Autowired
    private ExpertsDeclareService expertsDeclareService;

    @RequestMapping("/calculateResult")
    public ResponseResult<List> seclectAllExperts(@RequestBody ExpertsInfo expertsInfo) {
        log.info("开始执行专家申报方法");
        log.info("当前传入的申报系别值为：" + expertsInfo.getDeclareDept());
        log.info("当前传入的申报级别值为：" + expertsInfo.getExpertsDeclareRank());
        log.info("当前传入的年度值为：" + expertsInfo.getAnnual());
        //获取所有的专家申报信息id
        List<ExpertsInfo> errorList = new ArrayList<>();
        if (expertsInfo.getDeclareDept() != null && expertsInfo.getExpertsDeclareRank() != null && expertsInfo.getAnnual() != null) {
            List<ExpertsSelectResult> expertsSelectResultList = expertsDeclareService.getExpertsDeclareInfo(expertsInfo);
            return this.getOkResponseResult(expertsSelectResultList, "success");
        }

        return this.getOkResponseResult(errorList, "error");
    }

    // 专家申报明细
    @RequestMapping("/insertEdDetail")
    public ResponseResult<String> calculateReviewAppointmentComments(@RequestBody List<ExpertDeclareDetail> expertDeclareDetailList) {

        //获取聘任意见信息
        List<BizObjectModel> models = new ArrayList<>();
        for (ExpertDeclareDetail expertDeclareDetail : expertDeclareDetailList) {
            BizObjectModel model = new BizObjectModel();
            model.setSchemaCode("expertDeclareDetail");
            Map<String, Object> map = new HashMap<>();
            //专家申报id
            map.put("edDetail", expertDeclareDetail.getEdDetail());
            //聘任意见主表id
            map.put("edOpinionDetail", expertDeclareDetail.getEdOpinionDetail());
            // 表决结果
            map.put("voteResult", expertDeclareDetail.getVoteResult());
            //将数据写入到model
            model.put(map);
            model.setSequenceStatus("COMPLETED");
            models.add(model);
        }
        //创建数据的引擎类
        BizObjectFacade bizObjectFacade = super.getBizObjectFacade();
        String userId = UserUtils.getUserId(getUserId());
        log.info("当前操作的用户id为" + userId);
        //使用引擎方法批量创建数据
        bizObjectFacade.addBizObjects(userId, models, "id");
        return getOkResponseResult("success", ErrCode.OK.getErrMsg());
    }

    //计算每个专家的结果
    @RequestMapping("/calculationExpertResult")
    public ResponseResult<String> calculationAssessmentResult(@RequestParam String edoId, @RequestParam Integer passPerson, @RequestParam Integer passPoll, @RequestParam String oexpertsDeclareRank, @RequestParam Integer num) {
        //清空每个专家的表决结果
        expertsDeclareService.clearExpertsReult(edoId);

        List<String> peoples = expertsDeclareService.getExpertsDeclareDetailsNum(edoId);
        log.info("当前评委数：" + num + "当前已投票数：" + peoples.size());
        if (peoples.size() != num) {
            log.info("流程尚未结束，无法计算");
            return getErrResponseResult("失败", 404L, "流程尚未结束无需计算");
        }

        log.info("当前计算的专家申报意见的id值为：" + edoId);
        //获取全部的明细结果
        List<ExpertsResultDetail> expertsResultDetails = expertsDeclareService.findExpertsInfo(edoId);
        if (expertsResultDetails.size() == 0) {
            return getOkResponseResult("error", "列表为空");
        }
        //对申报专家进行初始化，计算票数
        Map<String, ExpertsDeclare> map = new HashMap<>();
        ExpertsDeclare expertsDeclare = null;
        for (ExpertsResultDetail erd :
                expertsResultDetails) {
            String edDetail = erd.getEdDetail();
            if (map.containsKey(edDetail)) {
                expertsDeclare = map.get(edDetail);
            } else {
                expertsDeclare = new ExpertsDeclare();
                expertsDeclare.setExpertsDeclareRank(oexpertsDeclareRank);
                expertsDeclare.setAgreePoll(0);
                expertsDeclare.setOpposePoll(0);
                expertsDeclare.setWaiverPoll(0);
                expertsDeclare.setId(edDetail);
                map.put(edDetail, expertsDeclare);
            }
            switch (erd.getVoteResult()) {
                case Points.AGREE_POLL:
                    expertsDeclare.setAgreePoll(expertsDeclare.getAgreePoll() + 1);
                    break;
                case Points.WAIVER_POLL:
                    expertsDeclare.setWaiverPoll(expertsDeclare.getWaiverPoll() + 1);
                    break;
                case Points.OPPOSE_POLL:
                    expertsDeclare.setOpposePoll(expertsDeclare.getOpposePoll() + 1);
                    break;
            }
        }
        List<ExpertsDeclare> shouldUpdateEd = new ArrayList<>();  // 需要更新的人员
        List<ExpertsDeclare> passED = new ArrayList<>();  // 存储达到票数的人员
        List<ExpertsDeclare> failED = new ArrayList<>();  // 存储没有达到票数的人员
        for (ExpertsDeclare ed :
                map.values()) {
            if (ed.getAgreePoll() >= passPoll) {
                passED.add(ed);
            } else {
                failED.add(ed);
            }
        }

        if (passED.size() >= passPerson) {
            if ("一级".equals(oexpertsDeclareRank)) {
                // 一级，降级
                for (ExpertsDeclare declare : failED) {
                    declare.setExpertsDeclareRank("二级");
                }
                shouldUpdateEd.addAll(failED);
            } else {
                for (ExpertsDeclare declare : failED) {
                    declare.setPollStatus("未通过");
                }
                shouldUpdateEd.addAll(failED);
            }
        }

        if (passED.size() < passPerson) {
            //通过的人数少于意见表设置的通过人数
            for (ExpertsDeclare declare : passED) {
                declare.setPollStatus("已通过");
            }
            shouldUpdateEd.addAll(passED);
            shouldUpdateEd.addAll(failED);
        } else if (passED.size() == passPerson) {
            //通过的人数刚好等于意见表设置的通过人数
            for (ExpertsDeclare declare : passED) {
                declare.setPollStatus("已通过");
            }
            shouldUpdateEd.addAll(passED);

        } else {
            //通过的人数大于意见表设置的通过人数
            int passNum = 0;
            //对投票数符合的专家进行遍历
            for (int j = 0; j < passED.size() - 1; j++) {
                if (j <= passPerson) {
                    if (passED.get(j).getAgreePoll() != passED.get(j + 1).getAgreePoll()) {
                        // 下一个的赞成票数和当前的赞成票数不相同
                        passED.get(j).setPollStatus("已通过");
                        shouldUpdateEd.add(passED.get(j));
                        passNum += 1;
                    } else {
                        // 下一个赞成票数和当前赞成票数相同，开始重复
                        int repeat = 0;
                        for (int k = j + 1; k < passED.size() - 1; k++) {
                            if (passED.get(j).getAgreePoll() != passED.get(j + 1).getAgreePoll()) {
                                // 重复值到k + 1的时候断开
                                break;
                            } else {
                                repeat += 1;
                            }
                        }
                        //从下一个开始的重复+当前的这个重复值+通过人数
                        if (passNum + 1 + repeat <= passPerson) {
                            // 直接添加
                            for (int k = 0; k < repeat; k++) {
                                passED.get(j + k).setPollStatus("已通过");
                                shouldUpdateEd.add(passED.get(j + k));
                                passNum += 1;
                            }
                        } else {
                            //加上重复的大于通过人数
                            // 在j处 进行重投
                            for (int k = 0; k < repeat; k++) {
                                shouldUpdateEd.add(passED.get(j + k));
                            }
                            //从当前专家到重复专家  之后的一级变成2级 二级改为未通过
                            for (int k = repeat + j; k < passED.size(); k++) {
                                if ("一级".equals(oexpertsDeclareRank)) {
                                    passED.get(k).setExpertsDeclareRank("二级");
                                    shouldUpdateEd.add(passED.get(k));
                                } else {
                                    passED.get(k).setPollStatus("未通过");
                                    shouldUpdateEd.add(passED.get(k));
                                }
                            }
                            break;
                        }
                    }
                } else {
                    //当前个专家超过设置的通过人数
                    //从当前专家 之后的一级变成2级 二级改为未通过
                    for (int k = j; k < passED.size(); k++) {
                        if ("一级".equals(oexpertsDeclareRank)) {
                            passED.get(k).setExpertsDeclareRank("二级");
                            shouldUpdateEd.add(passED.get(k));
                        } else {
                            passED.get(k).setPollStatus("未通过");
                            shouldUpdateEd.add(passED.get(k));
                        }
                    }
                    break;
                }
            }
        }

        //更新每个专家的票数
        expertsDeclareService.updateAllExpertsDeclare(shouldUpdateEd);

        return getOkResponseResult("success", "成功计算结果");  // 成功
    }

    //获取专家参评人选基本情况
    @RequestMapping("/getExpertsInfoList")
    // public ResponseResult<List> getExpertsInfoList(@Param("expertDeclareName") String expertDeclareName, @Param("expertsDeclareOrganization") String expertsDeclareOrganization){
    public ResponseResult<String> getExpertsInfoList(@RequestBody ExpertDeclareInfo expertDeclareInfo) {

        // List<ExpertsInfo> errorList = new ArrayList<>();
        //根据专家名称和单位查询一览表中是否有这个专家信息
        ExpertsInfoList expertsInfoList = expertsDeclareService.findExpertsFromInfoList(expertDeclareInfo.getExpertsDeclareName(), expertDeclareInfo.getExpertsDeclareOrganization());
        //通过专家姓名和单位在一览表没查到专家申报信息
        if (expertsInfoList == null) {
            //将专家信息添加到一览表
            //将每个专家的信息封装到专家信息一览表
            BizObjectModel model = new BizObjectModel();
            model.setSchemaCode("expertsInfoList");
            Map<String, Object> map = new HashMap<>();
            map.put("userName", expertDeclareInfo.getExpertsDeclareName());
            map.put("unit", expertDeclareInfo.getExpertsDeclareOrganization());
            map.put("gender",expertDeclareInfo.getGender() );
            map.put("birth", expertDeclareInfo.getDateOfBirth());
            map.put("post", expertDeclareInfo.getTechnicalPosts());
            map.put("positionalTitles", expertDeclareInfo.getPositionalTitles());
            map.put("firstEducation", expertDeclareInfo.getFirstEducation());
            map.put("graduatesAndMajors", expertDeclareInfo.getGraduationSchool() + " " + expertDeclareInfo.getFirstMajor());
            map.put("theHighestEducationDegree", expertDeclareInfo.getOfficialAcademicCredentials() + " " + expertDeclareInfo.getHighestMajor());
            map.put("nowMajorIn", expertDeclareInfo.getNowMajorIn());
            map.put("declareSessionDeptGrade", expertDeclareInfo.getAnnual() + " " + expertDeclareInfo.getDeclareDept() + " " + expertDeclareInfo.getExpertsDeclareRank());
            map.put("mainAchievements", expertDeclareInfo.getKeyPerformance());
            model.put(map);
            model.setSequenceStatus("COMPLETED");

            //创建数据的引擎类
            BizObjectFacade bizObjectFacade = super.getBizObjectFacade();
            String userId = UserUtils.getUserId(getUserId());
            log.info("当前操作的用户id为" + userId);
            //使用引擎方法批量创建数据
            String id = bizObjectFacade.saveBizObjectModel(userId, model, "id");
            //从专家申报的子表查询参评条件，可能存在多个
            List<ConditionsParticipations> conditionsParticipations = expertsDeclareService.getConditionsParticipations(expertDeclareInfo.getId());
            for (ConditionsParticipations conditionsParticipation : conditionsParticipations) {
                conditionsParticipation.setId(UUID.randomUUID().toString().replace("-", ""));
                conditionsParticipation.setParentId(id);
            }
            //在一栏表中批量插入参评条件
            expertsDeclareService.insertConditions(conditionsParticipations);

            return this.getOkResponseResult("success", "将专家信息添加到一览表成功");
        } else {
            return this.getOkResponseResult("error", "专家已在一览表中");
        }
    }
}
