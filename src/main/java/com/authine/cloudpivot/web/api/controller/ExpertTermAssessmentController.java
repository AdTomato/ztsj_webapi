package com.authine.cloudpivot.web.api.controller;

import com.authine.cloudpivot.web.api.bean.ExpertTermAssessment;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.service.ExpertTermAssessmentService;
import com.authine.cloudpivot.web.api.view.ResponseResult;

import java.util.List;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "专家任期考核", tags = "专家任期考核")
@RestController
@RequestMapping("/ext/expertTerm")
public class ExpertTermAssessmentController extends BaseController {

	@Autowired
	private ExpertTermAssessmentService expertTermAssessmentService;

	// 专家任期评分 同步 专家任期基本数据
	@GetMapping("/get")
	public ResponseResult<List<ExpertTermAssessment>> get(@RequestParam String majorCategories,
			@RequestParam String unit, @RequestParam String annual, @RequestParam String assessmentContent) {
		List<ExpertTermAssessment> list = expertTermAssessmentService.get(majorCategories, unit, annual,
				assessmentContent);
		return ResponseResult.<List<ExpertTermAssessment>>builder().errcode(200L).data(list).build();
	}

	// 专家任期评分回写数据
	@PostMapping("/post")
	public ResponseResult<String> post(@RequestParam String json) {
		expertTermAssessmentService.post(json);
		return ResponseResult.<String>builder().errcode(200L).data("success").build();
	}

	@GetMapping("/calc")
	public ResponseResult<String> calc(@RequestParam String pId) {
		expertTermAssessmentService.calc(pId);
		return ResponseResult.<String>builder().errcode(200L).data("success").build();
	}
}