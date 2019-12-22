package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.bean.ADComprehensiveAssessment;
import com.authine.cloudpivot.web.api.bean.AssessmentSummaryDetail;
import com.authine.cloudpivot.web.api.bean.DepartmentAssessment;
import com.authine.cloudpivot.web.api.bean.EvaluationTable;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.mapper.AssessmentDetailMapper;
import com.authine.cloudpivot.web.api.service.IAssessmentDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AssessmentDetail implements IAssessmentDetail{


    @Resource
    AssessmentDetailMapper assessmentDetailMapper;

    @Override
    public void insertAssessmentDetail(com.authine.cloudpivot.web.api.bean.AssessmentDetail assessmentDetail) {
        assessmentDetailMapper.insertAssessmentDetail(assessmentDetail);
    }

    @Override
    public List<ADComprehensiveAssessment> getADComprehensiveAssessmentByParentId(String parentId) {
        return assessmentDetailMapper.getADComprehensiveAssessmentByParentId(parentId);
    }

    @Override
    public Double getAssessmentDetailResultScore(String deartmentAssessment, String assessmentProject) {
        return assessmentDetailMapper.getAssessmentDetailResultScore(deartmentAssessment, assessmentProject);
    }

    @Override
    public void updateAssessmentDetailResultScore(String id, Double resultScore) {
        assessmentDetailMapper.updateAssessmentDetailResultScore(id, resultScore);
    }

    @Override
    public void insertAssessmentDetail(List<com.authine.cloudpivot.web.api.bean.AssessmentDetail> assessmentDetailList) {

    }

    /**
     * 根据部门和年度获取部门年度总结的id值
     * @param department
     * @param annual
     * @return
     */
    @Override
    public String getDepartmentAnnual(String department, String annual) {
        return assessmentDetailMapper.getDepartmentAnnual(department, annual);
    }

    /**
     * 批量创建机关部门考核评价表
     * @param evaluationTables  机关部门考核评价表
     */
    @Override
    public void insertEvaluationTable(List<EvaluationTable> evaluationTables) {
        assessmentDetailMapper.insertEvaluationTable(evaluationTables);
    }

    /**
     * 获取机关部门考核的流程状态
     * @param id    表单id
     * @return
     */
    @Override
    public String getDepartmentAnnualStatus(String id) {
        return assessmentDetailMapper.getDepartmentAnnualStatus(id);
    }

    /**
     * 清空机关部门考核的每个人的打分项
     * @param parentId  父id
     */
    @Override
    public void cleanAssessmentScore(String parentId) {
        assessmentDetailMapper.cleanAssessmentScore(parentId);
    }

    /**
     * 获取机关部门考核表
     * @param id id值
     * @return 机关部门考核表
     */
    public DepartmentAssessment getDepartmentAssessmentById(String id) {
        return assessmentDetailMapper.getDepartmentAssessmentById(id);
    }

    /**
     * 根据年度获取年度获取年度考核汇总表的id值
     * @param annual 年度
     * @return 年度考核汇总表的id
     */
    @Override
    public String getAssessmentIdByAnnual(String annual) {
        return assessmentDetailMapper.getAssessmentIdByAnnual(annual);
    }

    /**
     * 根据年度考核汇总表的id以及部门来获取汇总明细
     * @param parentId 年度考核汇总表的id
     * @param department 部门
     * @return 年度考核汇总表明细
     */
    @Override
    public AssessmentSummaryDetail getAssessmentDetailByParentIdAndDepartment(String parentId, String department) {
        return assessmentDetailMapper.getAssessmentDetailByParentIdAndDepartment(parentId, department);
    }

    /**
     * 根据年度考核汇总表的id值来更新年度考核汇总表明细
     * @param assessmentSummaryDetail 年度考核汇总表明细
     */
    @Override
    public void updateAssessmentDetailById(AssessmentSummaryDetail assessmentSummaryDetail) {
        assessmentDetailMapper.updateAssessmentDetailById(assessmentSummaryDetail);
    }

    /**
     * 新增年度考核汇总表明细
     * @param assessmentSummaryDetail 年度考核汇总表明细
     */
    @Override
    public void insertAssessmentSummaryDetail(AssessmentSummaryDetail assessmentSummaryDetail) {
        assessmentDetailMapper.insertAssessmentSummaryDetail(assessmentSummaryDetail);
    }


}
