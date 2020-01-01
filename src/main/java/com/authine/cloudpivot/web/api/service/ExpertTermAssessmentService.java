package com.authine.cloudpivot.web.api.service;

import com.authine.cloudpivot.web.api.bean.ExpertTermAssessment;

import java.util.List;

public interface ExpertTermAssessmentService {

	List<ExpertTermAssessment> get(String majorCategories, String unit, String annual, String assessmentContent);

	String post(String json);
	
	String calc(String id);
}
