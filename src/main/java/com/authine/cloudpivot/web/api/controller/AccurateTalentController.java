package com.authine.cloudpivot.web.api.controller;

import com.authine.cloudpivot.engine.api.facade.BizObjectFacade;
import com.authine.cloudpivot.engine.api.model.runtime.BizObjectModel;
import com.authine.cloudpivot.web.api.bean.AccurateTalentInfo;
import com.authine.cloudpivot.web.api.bean.AccurateTalentInfoList;
import com.authine.cloudpivot.web.api.bean.AtStudyExperience;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.service.AccurateTalentDeclareService;
import com.authine.cloudpivot.web.api.utils.UserUtils;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @Author:lfh
 * @Date:2020/2/2514:03
 * @Description: 紧缺人才重点培养对象申报控制层
 */
@RestController
@RequestMapping("/ext/AccurateTalentDeclare")
@Slf4j
public class AccurateTalentController extends BaseController{

    @Autowired
    private AccurateTalentDeclareService accurateTalentDeclareService;

    //紧缺人才重点培养对象信息表
    @RequestMapping("/getAccurateTalentList")
    public ResponseResult<String> getPersonTrainList(@RequestBody AccurateTalentInfo accurateTalentInfo) {


        //在紧缺人在重点培养对象信息表中通过单位查询紧缺人才重点培养对象
        AccurateTalentInfoList accurateTalentInfoList = accurateTalentDeclareService.findAccurateTalentInfoList(accurateTalentInfo.getCultivateName(),accurateTalentInfo.getWorkUnit());
        //紧缺人才重点培养对象无该紧缺人才
        if (accurateTalentInfoList == null) {
            //将紧缺人才信息添加到信息表

            //将紧缺人才信息封装到信息表中
            BizObjectModel model = new BizObjectModel();
            model.setSchemaCode("PersonTrainInfoSheet");
            Map<String, Object> map = new HashMap<>();
            map.put("userName", accurateTalentInfo.getCultivateName());
            map.put("placeTheUnit", accurateTalentInfo.getWorkUnit());
            map.put("talentClasses", accurateTalentInfo.getTalentCategory());
            map.put("gender", accurateTalentInfo.getGender());
            map.put("politicsStatus", accurateTalentInfo.getPoliticsStatus());
            map.put("nativePace", accurateTalentInfo.getNativePlace());
            map.put("dateOfBirth", accurateTalentInfo.getDateOfBirth());
            Date startTime = accurateTalentDeclareService.findWorkTimeFromAtWorkExperience(accurateTalentInfo.getId());
            map.put("workTime", startTime);
            map.put("theTitleOfTechenicalPost", accurateTalentInfo.getProfessionalTechnicalTitle());
            map.put("presentOccupation", accurateTalentInfo.getDuty());
            model.put(map);
            model.setSequenceStatus("COMPLETED");
            //创建数据的引擎类
            BizObjectFacade bizObjectFacade = super.getBizObjectFacade();
            String userId = UserUtils.getUserId(getUserId());
            log.info("当前操作的用户id为：" + userId);

            String id = bizObjectFacade.saveBizObject(userId, model, false);

            log.info("创建成功id为：" + id);
            //查询紧缺人才申报的子表学习经历
            List<AtStudyExperience> atStudyExperiences = accurateTalentDeclareService.getStudyExperience(accurateTalentInfo.getId());
            if (atStudyExperiences != null && !atStudyExperiences.isEmpty()) {
                for (AtStudyExperience atStudyExperience : atStudyExperiences) {
                    atStudyExperience.setId(UUID.randomUUID().toString().replace("-", ""));
                    atStudyExperience.setParentId(id);
                }
                //在信息表中批量插入学习经历
                accurateTalentDeclareService.insertEducationException(atStudyExperiences);
            }
            return this.getOkResponseResult("success", "紧缺人才申报添加到信息表成功");
        } else {
            return this.getOkResponseResult("error", "该紧缺人才已经在信息表中");
        }
    }

}
