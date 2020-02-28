package com.authine.cloudpivot.web.api.service;

import com.authine.cloudpivot.web.api.bean.AccurateTalentInfoList;
import com.authine.cloudpivot.web.api.bean.AtStudyExperience;

import java.util.Date;
import java.util.List;

/**
 * @Author:lfh
 * @Date:2020/2/2514:58
 * @Description: 紧缺人才重点培养对象申报控服务层
 */
public interface AccurateTalentDeclareService {
    /**
     * //在紧缺人在重点培养对象信息表中通过单位查询紧缺人才重点培养对象 可能会获取多个
     * @param userName,placeUnit
     * @return 从紧缺人才信息表中获取紧缺人才信息
     */
    AccurateTalentInfoList findAccurateTalentInfoList(String userName, String placeTheUnit);



    /**
     * 从工作经历表中获取工作时间
     * @param id
     * @return 工作时间
     */
    Date findWorkTimeFromAtWorkExperience(String id);

    /**
     * 查询紧缺人才申报的子表学习经历
     * @param id
     * @return
     */
    List<AtStudyExperience> getStudyExperience(String id);

    /**
     * 在信息表中批量插入学习经历
     * @param atStudyExperiences 学习经历
     */
    void insertEducationException(List<AtStudyExperience> atStudyExperiences);

}
