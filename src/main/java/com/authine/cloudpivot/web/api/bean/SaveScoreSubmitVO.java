package com.authine.cloudpivot.web.api.bean;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.util.List;

@Data
public class SaveScoreSubmitVO {
    @JsonAlias("user_id")
    private String userId;
    @JsonAlias("dept_effect")
    private List<DeptEffect> deptEffectList;
    @JsonAlias("assessment_year")
    private int assessmentYear;
    @JsonAlias("assessment_season")
    private String assessmentSeason;
    @JsonAlias("seasonassessmentId")
    private String seasonassessmentId;
}
