package com.authine.cloudpivot.web.api.controller;

import com.authine.cloudpivot.web.api.bean.EvaluatingCadres;
import com.authine.cloudpivot.web.api.bean.EvaluatingCadresList;
import com.authine.cloudpivot.web.api.bean.SEvaluatingCadresList;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.service.EvaluatingCadresService;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:lfh
 * @Date: 2020/1/7 10:59
 * @Description： 新选拔干部民主评议表控制层
 */
@RestController
@RequestMapping("/ext/evaluatingCadres")
@Slf4j
public class EvaluatingCadresController extends BaseController {

    @Autowired
    private EvaluatingCadresService evaluatingCadresService;

    @RequestMapping("/calculateResoult")
    public ResponseResult<Void> calculateResult(@RequestParam("id") String id) {
        log.info("开始执行新选拔干部民主评议表方法");
        log.info("当前传入的id值为：" + id);
        //获取发起新选拔干部民主评议表的全部信息
        EvaluatingCadres ec = evaluatingCadresService.getEvaluatingCadresInfo(id);
        Map map = new HashMap();
        map.put("id", ec.getId());
        map.put("max", ec.getParticipantsPeoples());
        map.put("createdTime", ec.getCreatedTime());
        //根据unit获取从0到最大投票人数的新选拔干部民主评议表的id
        List<String> idList = evaluatingCadresService.getEvaluatingCadresIdByUnit(map);
        log.info("获取的是0-" + ec.getParticipantsPeoples() + "的新选拔干部民主评议表信息");
        log.info("获取的id列表是" + idList);

        //计算评测干部表
        calculateEvaluatingCadresList(idList, id);
        //更新发起新选拔干部民主评议表主表结果
        Map<String, Object> info = new HashMap<>();
        info.put("votoPeoples", idList.size());
        info.put("id", id);
        evaluatingCadresService.updateEvaluatingCadresInfo(info);
        log.info("新选拔干部民主评议表票结果计算完毕");
        return getOkResponseResult("计算完毕");
    }

    /**
     * 计算评测干部表
     *
     * @param idList
     * @param id
     */
    private void calculateEvaluatingCadresList(List<String> idList, String id) {
        log.info("开始计算新选拔干部民主评议表中评测干部表");

        //获取全部的发起新选拔干部民主评议表的 评测干部表信息
        List<SEvaluatingCadresList> sec = evaluatingCadresService.getAllSEvaluatingCadresListData(id);

        //获取全部的新选拔干部民主评议表的 评测干部表信息
        List<EvaluatingCadresList> ec = evaluatingCadresService.getAllEvaluatingCadresListData(id);

        Map<String, SEvaluatingCadresList> secMap = new HashMap<>();
        //初始化评测干部信息表
        for (SEvaluatingCadresList secList : sec) {
            secList.setSatisfiedPoll(0);
            secList.setBasicSatisfiedPoll(0);
            secList.setNoSatisfiedPoll(0);
            secList.setNoUnderstandPoll(0);
            secList.setExistencePoll(0);
            secList.setNoExistencePoll(0);
            secList.setINoUnderstandPoll(0);
            secMap.put(secList.getUserName() + secList.getDateOfBirth() + secList.getRawDuty() + secList.getCashDuty(), secList);
        }
        //计算给个干部的票数
        for (EvaluatingCadresList ecList : ec) {
            if (idList.contains(ecList.getParentId())) {
                SEvaluatingCadresList secList = secMap.get(ecList.getUserName() + ecList.getDateOfBirth() + ecList.getRawDuty() + ecList.getCashDuty());
                if (null != secList) {
                    switch (ecList.getPerspective()) {
                        case "满意":
                            secList.setSatisfiedPoll(secList.getSatisfiedPoll() + 1);
                            break;
                        case "基本满意":
                            secList.setBasicSatisfiedPoll(secList.getBasicSatisfiedPoll() + 1);
                            break;
                        case "不满意":
                            secList.setNoSatisfiedPoll(secList.getNoSatisfiedPoll() + 1);
                            break;
                        case "不了解":
                            secList.setNoUnderstandPoll(secList.getNoUnderstandPoll() + 1);
                            break;
                    }

                    switch (ecList.getIfThereIsA()) {
                        case "不存在":
                            secList.setNoExistencePoll(secList.getNoExistencePoll() + 1);
                            break;
                        case "存在":
                            secList.setExistencePoll(secList.getExistencePoll() + 1);
                            break;
                        case "不了解":
                            secList.setINoUnderstandPoll(secList.getINoUnderstandPoll() + 1);
                            break;
                    }
                }
            }
        }

        //更新最终结果
        evaluatingCadresService.updateAllEvaluatingCadres(sec);
        log.info("计算新选拔干部民主评议表中评测干部表完成");

    }
}
