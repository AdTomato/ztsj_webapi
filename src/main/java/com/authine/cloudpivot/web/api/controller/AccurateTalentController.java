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
    public ResponseResult<String> getPersonTrainList(String workUnit){


        //在紧缺人在重点培养对象信息表中通过单位查询紧缺人才重点培养对象 可能会获取多个
        List<AccurateTalentInfoList> accurateTalentInfoList = accurateTalentDeclareService.findAccurateTalentInfoList(workUnit);
        //紧缺人才重点培养对象无该紧缺人才
        if (accurateTalentInfoList.size() == 0 || accurateTalentInfoList == null){
            //将紧缺人才信息添加到信息表
            //从紧缺人才申报表中获取紧缺人才信息，可能获取多个人
            List<AccurateTalentInfo> talentsList = accurateTalentDeclareService.findTalentsFromAccurateTalent(workUnit);
            List<BizObjectModel> models = new ArrayList<>();
            for (AccurateTalentInfo accurateTalentInfo : talentsList) {
                //将紧缺人才信息封装到信息表中
                BizObjectModel model = new BizObjectModel();
                model.setSchemaCode("PersonTrainInfoSheet");
                Map<String,Object> map = new HashMap<>();
                String id = UUID.randomUUID().toString().replace("-","" );
                map.put("id", id);
                map.put("userName",accurateTalentInfo.getCultivateName());
                map.put("placeTheUnit", accurateTalentInfo.getWorkUnit());
                map.put("talentClasses",accurateTalentInfo.getTalentCategory());
                map.put("gender",accurateTalentInfo.getGender());
                map.put("politicsStatus", accurateTalentInfo.getPoliticsStatus());
                map.put("nativePace",accurateTalentInfo.getNativePlace() );
                map.put("dateOfBirth", accurateTalentInfo.getDateOfBirth());
                Date startTime = accurateTalentDeclareService.findWorkTimeFromAtWorkExperience(accurateTalentInfo.getId());
                map.put("workTime", startTime);
                map.put("theTitleOfTechenicalPost", accurateTalentInfo.getProfessionalTechnicalTitle());
                map.put("presentOccupation",accurateTalentInfo.getDuty());
                model.put(map);
                model.setSequenceStatus("COMPLETED");
                models.add(model);
                //查询紧缺人才申报的子表学习经历
                List<AtStudyExperience> atStudyExperiences = accurateTalentDeclareService.getStudyExperience(accurateTalentInfo.getId());
                if (atStudyExperiences != null && !atStudyExperiences.isEmpty()){
                    for (AtStudyExperience atStudyExperience : atStudyExperiences) {
                        atStudyExperience.setId(UUID.randomUUID().toString().replace("-","" ));
                        atStudyExperience.setParentId(id);
                    }
                    //在信息表中批量插入学习经历
                    accurateTalentDeclareService.insertEducationException(atStudyExperiences);
                }

            }
            //创建数据的引擎类
            BizObjectFacade bizObjectFacade = super.getBizObjectFacade();
            String userId = UserUtils.getUserId(getUserId());
            log.info("当前操作的用户id为："+userId);
            //使用引擎方法批量创建数据
            bizObjectFacade.addBizObjects(userId,models ,"id" );
            return this.getOkResponseResult("success", "紧缺人才申报添加到信息表成功");
        }else {
            if (accurateTalentInfoList.size()>1){
                return this.getOkResponseResult("error","查询到多个紧缺人才信息，手动输入数据" );
            }
            return this.getOkResponseResult("success","该紧缺人才已经在信息表中" );
        }

    }
}
