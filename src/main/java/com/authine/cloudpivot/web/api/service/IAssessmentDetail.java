package com.authine.cloudpivot.web.api.service;


import com.authine.cloudpivot.web.api.bean.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: wangyong
 * @Date: 2019/12/15
 * @Description: 机关部门考核接口
 */
public interface IAssessmentDetail {
    /**
     * 插入机关部门考核明细
     *
     * @param assessmentDetail 机关部门考核明细
     */
    public void insertAssessmentDetail(AssessmentDetail assessmentDetail);

    /**
     * 根据parentId获取局机关部门综合评价表
     *
     * @param parentId
     * @return
     */
    public List<ADComprehensiveAssessment> getADComprehensiveAssessmentByParentId(String parentId);

    /**
     * 根据关联机关部门考核和测评项目查找机关部门考核明细
     *
     * @param deartmentAssessment 关联机关部门考核
     * @param assessmentProject   测评项目
     * @return
     */
    public Double getAssessmentDetailResultScore(String deartmentAssessment, String assessmentProject);

    /**
     * 更新机关部门考核明细中的结果，根据id值进行更新
     *
     * @param id
     * @param resultScore
     */
    public void updateAssessmentDetailResultScore(String id, Double resultScore);

    /**
     * 批量插入机关部门考核明细
     *
     * @param assessmentDetailList
     */
    public void insertAssessmentDetail(List<AssessmentDetail> assessmentDetailList);

    /**
     * 根据部门和年度获取部门年度总结的id值
     *
     * @param department 部门
     * @param annual     年度
     * @return 年度总结ID
     */
    public String getDepartmentAnnual(String department, String annual);

    /**
     * 批量创建机关部门考核评价表
     *
     * @param evaluationTables 机关部门考核评价表
     */
    public void insertEvaluationTable(List<EvaluationTable> evaluationTables);

    /**
     * 获取机关部门考核的流程状态
     *
     * @param id 表单id
     * @return
     */
    public String getDepartmentAnnualStatus(String id);

    /**
     * 清空机关部门考核的每个人的打分项
     *
     * @param parentId 父id
     */
    public void cleanAssessmentScore(String parentId);

    /**
     * 获取机关部门考核表
     *
     * @param id id值
     * @return 机关部门考核表
     */
    public DepartmentAssessment getDepartmentAssessmentById(String id);

    /**
     * 根据年度获取年度获取年度考核汇总表的id值
     *
     * @param annual 年度
     * @return 年度考核汇总表的id
     */
    public String getAssessmentIdByAnnual(String annual);

    /**
     * 根据年度考核汇总表的id以及部门来获取汇总明细
     *
     * @param parentId   年度考核汇总表的id
     * @param department 部门
     * @return 年度考核汇总表明细
     */
    public AssessmentSummaryDetail getAssessmentDetailByParentIdAndDepartment(String parentId, String department);

    /**
     * 根据年度考核汇总表的id值来更新年度考核汇总表明细
     *
     * @param assessmentSummaryDetail 年度考核汇总表明细
     */
    public void updateAssessmentDetailById(AssessmentSummaryDetail assessmentSummaryDetail);

    /**
     * 新增年度考核汇总表明细
     *
     * @param assessmentSummaryDetail 年度考核汇总表明细
     */
    public void insertAssessmentSummaryDetail(AssessmentSummaryDetail assessmentSummaryDetail);

    public Integer isCreateAssessmentDetail(Map map);

}
