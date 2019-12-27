package com.authine.cloudpivot.web.api.utils;

import com.authine.cloudpivot.engine.api.facade.BizObjectFacade;
import com.authine.cloudpivot.engine.api.model.runtime.BizObjectModel;
import com.authine.cloudpivot.web.api.bean.AssessmentResult;
import com.authine.cloudpivot.web.api.controller.base.BaseController;

import java.util.*;

/**
 * @Author: wangyong
 * @Date: 2019-12-22 09:42
 * @Description: 用于创建领导人员考核结果的工具类,该类继承与云枢引擎的BaseController类
 */
public class CreateAssessmentResultUtils extends BaseController {


    /**
     * @Author: wangyong
     * @Date: 2019/12/22 9:49
     * @param userId : 创建者id
     * @param leaderPersonId : 领导人员的ID值
     * @param assessContent : 考核内容
     * @param assessTime : 考核时间
     * @param assessResult : 考核结果
     * @return : java.lang.String 领导人员考核结果id
     * @Description: 用于创建领导人员考核结果
     */
    public static String createAssessmentResult(BizObjectFacade objectFacade, String userId, String leaderPersonId, String assessContent, Date assessTime, String assessResult) {
        BizObjectModel model = new BizObjectModel();
        model.setSchemaCode("AssessmentResult");
        Map data = new HashMap();
        data.put("leadershipPerson", leaderPersonId);
        data.put("assess_content", assessContent);
        data.put("assess_time", assessTime);
        data.put("assess_result", assessResult);
        model.put(data);
        model.setSequenceStatus("COMPLETED");
        return objectFacade.saveBizObjectModel(userId, model, "id");
    }

    /**
     * @Author: wangyong
     * @Date: 2019/12/26 14:19
     * @param userId : 用户id
     * @param arList : 考核结果集合
     * @return : java.lang.String[] 领导人员考核结果id集合
     * @Description: 用于创建领导人员考核结果
     */
    public static List<String> createAssessmentResults(BizObjectFacade objectFacade, String userId, List<AssessmentResult> arList) {

        List<BizObjectModel> bizObjects = new ArrayList<>();

        for (AssessmentResult ar :
                arList) {
            BizObjectModel model = new BizObjectModel();
            model.setSchemaCode("AssessmentResult");
            model.setSequenceStatus("COMPLETED");
            Map data = new HashMap();
            data.put("leadershipPerson", ar.getLeadershipPerson());
            data.put("assess_content", ar.getAssessContent());
            data.put("assess_time", ar.getAssessTime());
            data.put("assess_result", ar.getAssessResult());
            model.put(data);
            bizObjects.add(model);
        }
        return objectFacade.addBizObjects(userId, bizObjects, "id");
    }

}
