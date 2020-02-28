package com.authine.cloudpivot.web.api.mapper;

import com.authine.cloudpivot.web.api.bean.AccurateTalentInfo;
import com.authine.cloudpivot.web.api.bean.AccurateTalentInfoList;
import com.authine.cloudpivot.web.api.bean.AtStudyExperience;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author:lfh
 * @Date:2020/2/25 16:32
 * @Description: 紧缺人才重点培养mapper
 */

public interface AccurateTalentDeclareMapper {

    /**
     * //在紧缺人在重点培养对象信息表中通过单位查询紧缺人才重点培养对象 可能会获取多个
     *
     * @param map 姓名和工作单位
     * @return 从紧缺人才信息表中获取紧缺人才信息
     */
    AccurateTalentInfoList findAccurateTalentInfoList(Map map);

    /**
     * 从工作经历表中获取工作时间
     *
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
     * @param atStudyExperiences
     */
    void insertEducationException(List<AtStudyExperience> atStudyExperiences);

    /**
     * 从紧缺人才申报表中获取紧缺人才信息，不同单位可能获取多个人
     * @param workUnit
     * @return
     */
    List<AccurateTalentInfo> findTalentsFromAccurateTalent(String workUnit);
}
