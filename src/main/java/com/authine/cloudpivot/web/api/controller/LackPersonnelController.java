package com.authine.cloudpivot.web.api.controller;

import com.authine.cloudpivot.web.api.bean.*;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.service.LackPersonnelService;
import com.authine.cloudpivot.web.api.utils.DoubleUtils;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ext/lackpersonnelannual")
public class LackPersonnelController extends BaseController {
    private Logger log = LoggerFactory.getLogger(LackPersonnelController.class);
    @Autowired
    private LackPersonnelService lackPersonnelService;

    /**
     * 紧缺人才考核发起开会流程时,列表的自动填充
     *
     * @param lackPersonnelAssessInfo
     * @return
     */
    @RequestMapping("/personnelslist")
    public ResponseResult<List> findlist(@RequestBody LackPersonnelAssessInfo lackPersonnelAssessInfo) {
        String userId = this.getUserId();
        List<ExpertsInfo> errorList = new ArrayList<>();
        if (lackPersonnelAssessInfo.getAnnual().length() == 0 || lackPersonnelAssessInfo.getAnnual() == null || lackPersonnelAssessInfo.getDept().length() == 0 || lackPersonnelAssessInfo.getDept() == null) {
            return this.getOkResponseResult(errorList, "error");
        }
        List<LackPersonnelInfo> lackPersonnelList = lackPersonnelService.findLackPersonnelList(lackPersonnelAssessInfo);
        List<String> idtypes = new ArrayList<>();
        for (int i = 0; i < lackPersonnelList.size(); i++) {
            idtypes.add(lackPersonnelList.get(i).getNameIdType());
        }


        return this.getOkResponseResult(idtypes, "success");


    }

    /**
     * 紧缺人才主责部门存储分数
     *
     * @param lackPersonnelapplyInfo
     * @return
     */
    @RequestMapping("/savescore")
    public ResponseResult<String> savescore(@RequestBody LackPersonnelapplyinfo lackPersonnelapplyInfo) {
        String userId = "阿豪";
        String applyId = lackPersonnelapplyInfo.getApplyId();
        lackPersonnelapplyInfo.setUserId(userId);
        try {
            lackPersonnelService.savescore(lackPersonnelapplyInfo);
            lackPersonnelService.resetmaindutyscore(applyId);
            return this.getOkResponseResult("success", "存储分数成功");

        } catch (Exception e) {
            log.error(e.getMessage());
            return this.getOkResponseResult("error", "存储分数出错");
        }

    }

    /**
     * 计算主责部门分数 然后更新
     * @param lackPersonnelapplyInfo
     * @return
     */
    @RequestMapping("/countmaindutyscore")
    public ResponseResult<String> countscore(@RequestBody LackPersonnelapplyinfo lackPersonnelapplyInfo) {
        String userId = "阿豪";
        String applyId = lackPersonnelapplyInfo.getApplyId();
        lackPersonnelapplyInfo.setUserId(userId);
        try {
            lackPersonnelService.countscore(lackPersonnelapplyInfo);
            return this.getOkResponseResult("success", "存储分数成功");

        } catch (Exception e) {
            log.error(e.getMessage());
            return this.getOkResponseResult("error", "存储分数出错");
        }

    }

    @RequestMapping("/countfinalscore")
    public ResponseResult<String> countfinalscore(@RequestBody LackPersonnelapplyinfo lackPersonnelapplyInfo) {
        String userId = "阿豪";
        String applyId = lackPersonnelapplyInfo.getApplyId();
        lackPersonnelapplyInfo.setUserId(userId);
        try {
            FinalTotalResult countfinalscore = lackPersonnelService.countfinalscore(lackPersonnelapplyInfo);
            /*double ann_train = countfinalscore.getAnn_trainexamination_score().doubleValue();
            double depttotal = countfinalscore.getDept_total().doubleValue();
            double tutortotal = countfinalscore.getTutor_total().doubleValue();
            double executivetotal = countfinalscore.getExecutive_total().doubleValue();
            double maindutytoal = countfinalscore.getMainduty_total().doubleValue();*/
            double ann_train = countfinalscore.getAnnTrainexaminationScore().doubleValue();
            double depttotal = countfinalscore.getDeptTotal().doubleValue();
            double tutortotal = countfinalscore.getTutorTotal().doubleValue();
            double executivetotal = countfinalscore.getExecutiveTotal().doubleValue();
            double maindutytoal = countfinalscore.getMaindutyTotal().doubleValue();
            Double result = DoubleUtils.doubleRound(ann_train * 0.1 + depttotal * 0.45 + tutortotal * 0.15 + executivetotal * 0.15 + maindutytoal * 0.15, 2);
            lackPersonnelService.updateFinalscore(result,applyId);


            return this.getOkResponseResult("success", "存储总分成功");

        } catch (Exception e) {
            log.error(e.getMessage());
            return this.getOkResponseResult("error", "存储总分出错");
        }

    }
}
