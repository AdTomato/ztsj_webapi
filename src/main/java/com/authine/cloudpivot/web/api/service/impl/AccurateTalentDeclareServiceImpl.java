package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.bean.AccurateTalentInfo;
import com.authine.cloudpivot.web.api.bean.AccurateTalentInfoList;
import com.authine.cloudpivot.web.api.bean.AtStudyExperience;
import com.authine.cloudpivot.web.api.mapper.AccurateTalentDeclareMapper;
import com.authine.cloudpivot.web.api.service.AccurateTalentDeclareService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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
     * @param workUnit 工作单位
     * @return 从紧缺人才信息表中获取紧缺人才信息
     */
    @Override
    public List<AccurateTalentInfoList> findAccurateTalentInfoList(String workUnit) {

        return accurateTalentDeclareMapper.findAccurateTalentInfoList(workUnit);
    }

    /**
     * //从紧缺人才申报表中获取紧缺人才信息，不同单位可能获取多个人
     * @param workUnit
     * @return 从紧缺人才申报表中获取紧缺人才信息
     */
    @Override
    public List<AccurateTalentInfo> findTalentsFromAccurateTalent(String workUnit) {
        return accurateTalentDeclareMapper.findTalentsFromAccurateTalent(workUnit);
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
