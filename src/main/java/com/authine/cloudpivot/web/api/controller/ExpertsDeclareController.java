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
import io.swagger.models.auth.In;
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
public class ExpertsDeclareController extends BaseController{

    @Autowired
    private ExpertsDeclareService expertsDeclareService;

    @RequestMapping("/calculateResult")
    public ResponseResult<List> seclectAllExperts(@RequestBody ExpertsInfo expertsInfo) {
        log.info("开始执行专家申报方法");
        log.info("当前传入的申报系别值为：" + expertsInfo.getDeclareDept());
        log.info("当前传入的申报级别值为：" + expertsInfo.getOexpertsDeclareRank());
        log.info("当前传入的年度值为：" + expertsInfo.getOannual());
        //获取所有的专家申报信息id
        List<ExpertsInfo> errorList = new ArrayList<>();
        if (expertsInfo.getDeclareDept()!=null && expertsInfo.getOexpertsDeclareRank() !=null && expertsInfo.getOannual() !=null){
            List<ExpertsSelectResult> expertsSelectResultList= expertsDeclareService.getExpertsDeclareInfo(expertsInfo);
            return this.getOkResponseResult(expertsSelectResultList,"success" );
        }

        return this.getOkResponseResult(errorList, "error");
    }

    // 专家申报明细
    @RequestMapping("/insertEdDetail")
    public ResponseResult<String> calculateReviewAppointmentComments(@RequestBody ExpertsDeclareOpinion expertsDeclareOpinion) {
        if (expertsDeclareOpinion.getDeclareDept()!=null && expertsDeclareOpinion.getOexpertsDeclareRank() !=null && expertsDeclareOpinion.getOannual() !=null){
            return this.getOkResponseResult("失败","error" );
        }
        //获取聘任意见信息
        List<ReviewAppointmentComments> opinionList = expertsDeclareOpinion.getReviewAppointmentCommentsList();
        List<BizObjectModel> models = new ArrayList<>();
        for (ReviewAppointmentComments opinion : opinionList) {
            BizObjectModel model = new BizObjectModel();
            model.setSchemaCode("expert_declare_detail");
            Map<String,Object> map = new HashMap<>();
            //专家申报id
            map.put("edDetail",opinion.getEId());
            //聘任意见主表id
            map.put("edOpinionDetail", expertsDeclareOpinion.getId());
            // 表决结果
            map.put("voteResult", opinion.getDeclareOpinion());
            //将数据写入到model
            model.put(map);
            model.setSequenceStatus("COMPLETED");
            models.add(model);
        }
        //创建数据的引擎类
        BizObjectFacade bizObjectFacade = super.getBizObjectFacade();
        String userId = UserUtils.getUserId(getUserId());
        log.info("当前操作的用户id为"+userId);
        //使用引擎方法批量创建数据
        bizObjectFacade.addBizObjects(userId,models,"id" );
        return getOkResponseResult("success", ErrCode.OK.getErrMsg());
    }

    //计算每个专家的结果
    @RequestMapping("/calculationExpertResult")
    public ResponseResult<String> calculationAssessmentResult(@RequestParam("edoId") String edoId, @RequestParam Integer passPerson, @RequestParam Integer passPoll, @RequestParam String oexpertsDeclareRank) {
        //清空每个专家的表决结果
        expertsDeclareService.clearExpertsReult(edoId);

        String status = expertsDeclareService.getExpertsDeclareStatus(edoId);
        if (!SequenceStatusUtils.isCompleted(status)) {
            return getErrResponseResult("失败", 404L, "流程尚未结束无需计算");
        }

        log.info("当前计算的专家申报意见的id值为：" + edoId);
        //log.info("当前计算的专家的id值为：" + eId);
        //获取全部的明细结果
        List<ExpertsResultDetail> expertsResultDetails = expertsDeclareService.findExpertsInfo(edoId);
        if (expertsResultDetails.size() == 0) {
            return getOkResponseResult("error", "列表为空");
        }

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
            for (ExpertsDeclare declare : passED) {
                declare.setPollStatus("已通过");
            }
            shouldUpdateEd.addAll(passED);
            shouldUpdateEd.addAll(failED);
        } else if (passED.size() == passPerson) {
            shouldUpdateEd.addAll(passED);
        } else {
            int passNum = 0;
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
                        if (passNum + 1 + repeat <= passPerson) {
                            // 直接添加
                            for (int k = 0; k < repeat; k++) {
                                passED.get(j + k).setPollStatus("已通过");
                                shouldUpdateEd.add(passED.get(j + k));
                                passNum += 1;
                            }
                        } else {
                            // 在j处
                            for (int k = 0; k < repeat; k++) {
                                shouldUpdateEd.add(passED.get(j + k));
                            }
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


        /*List<String> eIdList = new ArrayList<>();
        for (ExpertsResultDetail expertsResultDetail : expertsResultDetails) {
            if (!eIdList.contains(expertsResultDetail.getEId())){
                eIdList.add(expertsResultDetail.getEId());
            }
        }*/

        //计算每个专家的票数
//        expertsDeclareService.updateExpertPoll(expertsResultDetails);
        expertsDeclareService.updateAllExpertsDeclare(shouldUpdateEd);

        return getOkResponseResult("success", "成功计算结果");  // 成功
    }

}
