package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.bean.AccurateTalentInfoList;
import com.authine.cloudpivot.web.api.bean.AtStudyExperience;
import com.authine.cloudpivot.web.api.mapper.AccurateTalentDeclareMapper;
import com.authine.cloudpivot.web.api.service.AccurateTalentDeclareService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:lfh
 * @Date:2020/2/25 14:59
 * @Description: 紧缺人才重点培养对象申报服务层
 */
@Service
public class AccurateTalentDeclareServiceImpl implements AccurateTalentDeclareService {

    @Resource
    private AccurateTalentDeclareMapper accurateTalentDeclareMapper;

    /**
     * //在紧缺人在重点培养对象信息表中通过单位查询紧缺人才重点培养对象 可能会获取多个
     * @param  userName placeTheUnit 姓名和工作单位
     * @return 从紧缺人才信息表中获取紧缺人才信息
     */
    @Override
    public AccurateTalentInfoList findAccurateTalentInfoList(String userName,String placeTheUnit) {

        Map<String,Object> map = new HashMap();
        map.put("userName",userName );
        map.put("placeUnit",placeTheUnit );
        return accurateTalentDeclareMapper.findAccurateTalentInfoList(map);
    }



    /**
     * 从工作经历表中获取工作时间
     * @param id
     * @return 工作时间
     */
    @Override
    public Date findWorkTimeFromAtWorkExperience(String id) {
        return accurateTalentDeclareMapper.findWorkTimeFromAtWorkExperience(id);
    }

    /**
     * 查询紧缺人才申报的子表学习经历
     * @param id
     * @return
     */
    @Override
    public List<AtStudyExperience> getStudyExperience(String id) {
        return accurateTalentDeclareMapper.getStudyExperience(id);
    }

    /**
     * 在信息表中批量插入学习经历
     * @param atStudyExperiences 学习经历
     */
    @Override
    public void insertEducationException(List<AtStudyExperience> atStudyExperiences) {
        accurateTalentDeclareMapper.insertEducationException(atStudyExperiences);
    }
}
