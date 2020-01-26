package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

import java.util.Date;

/**
 * @Author:lfh
 * @Date: 2020/1/19 14:41
 * @Description： 专家申报信息
 */
@Data
public class ExpertDeclareInfo {
    private String id;
    private String expertsDeclareName;
    private String expertsDeclareOrganization;
    private String gender;
    private Date dateOfBirth;
    private String technicalPosts;
    private String positionalTitles;
    private String firstEducation;
    private String graduationSchool;
    private String firstMajor;
    private String officialAcademicCredentials;
    private String schoolOfGraduation;
    private String highestMajor;
    private String nowMajorIn;
    private String annual;
    private String declareDept;
    private String expertsDeclareRank;
    private String keyPerformance;
}
