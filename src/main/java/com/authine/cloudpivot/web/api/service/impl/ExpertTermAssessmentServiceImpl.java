package com.authine.cloudpivot.web.api.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.authine.cloudpivot.web.api.bean.ExpertAssessmentDetail;
import com.authine.cloudpivot.web.api.bean.ExpertTermAssessment;
import com.authine.cloudpivot.web.api.mapper.ExpertAssessmentDetailMapper;
import com.authine.cloudpivot.web.api.mapper.ExpertTermAssessmentMapper;
import com.authine.cloudpivot.web.api.service.ExpertTermAssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class ExpertTermAssessmentServiceImpl implements ExpertTermAssessmentService {

    @Autowired
    private ExpertTermAssessmentMapper expertTermAssessmentMapper;
    @Autowired
    private ExpertAssessmentDetailMapper expertAssessmentDetailMapper;

    @Override
    public List<ExpertTermAssessment> get(String majorCategories, String unit, String annual,
                                          String assessmentContent) {

        Example ex = new Example(ExpertTermAssessment.class);
        Criteria criteria = ex.createCriteria();
        criteria.andEqualTo("majorCategories", majorCategories);
        criteria.andEqualTo("unit", unit);
        criteria.andEqualTo("annual", annual);
        criteria.andEqualTo("assessmentContent", assessmentContent);
        List<ExpertTermAssessment> list = expertTermAssessmentMapper.selectByExample(ex);
        return list;
    }

    @Override
    public String post(String json) {

        JSONArray jsonArray = JSONArray.parseArray(json);
        for (Object j : jsonArray) {
            ExpertAssessmentDetail e = (ExpertAssessmentDetail) j;
            e.setId(UUID.randomUUID().toString().replace("-", ""));
            expertAssessmentDetailMapper.insertSelective(e);
        }
        return "success";
    }

    @Override
    public String calc(String pId) {

        Example ex = new Example(ExpertAssessmentDetail.class);
        Criteria criteria = ex.createCriteria();
        criteria.andEqualTo("pId", pId);
        List<ExpertAssessmentDetail> list = expertAssessmentDetailMapper.selectByExample(ex);
        int size = list.size();
        if (size == 0) {
            return "success";
        }
        BigDecimal performDuties = new BigDecimal(0);
        BigDecimal innovationWork = new BigDecimal(0);
        BigDecimal establishNewSystem = new BigDecimal(0);
        BigDecimal talentCultivate = new BigDecimal(0);
        for (ExpertAssessmentDetail e : list) {
            performDuties = performDuties.add(e.getPerformDuties());
            innovationWork = innovationWork.add(e.getInnovationWork());
            establishNewSystem = establishNewSystem.add(e.getEstablishNewSystem());
            talentCultivate = talentCultivate.add(e.getTalentCultivate());
        }
        // 回写
        ExpertTermAssessment term = new ExpertTermAssessment();
        term.setId(pId);
        if (list.get(0).getAssessmentContent().startsWith("所在单位")) {
            term.setDEstablishNewSystem(establishNewSystem.divide(new BigDecimal(size), 2));
            term.setDInnovationWork(innovationWork.divide(new BigDecimal(size), 2));
            term.setDPerformDuties(performDuties.divide(new BigDecimal(size), 2));
            term.setDTalentCultivate(talentCultivate.divide(new BigDecimal(size), 2));
        } else {
            term.setBEstablishNewSystem(establishNewSystem.divide(new BigDecimal(size), 2));
            term.setBInnovationWork(innovationWork.divide(new BigDecimal(size), 2));
            term.setBPerformDuties(performDuties.divide(new BigDecimal(size), 2));
            term.setBTalentCultivate(talentCultivate.divide(new BigDecimal(size), 2));
        }
        expertTermAssessmentMapper.updateByPrimaryKeySelective(term);
        return "success";
    }

}
