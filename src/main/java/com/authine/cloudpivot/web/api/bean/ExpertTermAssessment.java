package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Table(name = "i6rlc_expert_term_assessment")
public class ExpertTermAssessment {

	@Id
	private String id;
	@Column(name="major_categories")
	private String majorCategories;  //所属大类
	private String unit;  //工作单位
	private String annual;  //年度
	@Column(name="assessment_content")
	private String assessmentContent;  //考核主体
	
	
	@Column(name="expert_level")
	private String expertLevel;  //专家等级
	@Column(name="unit_name")
	private String unitName;  //单位
	
	
	
	
	@Column(name="d_perform_duties")
	private BigDecimal dPerformDuties;  	
	@Column(name="d_innovation_work")
	private BigDecimal dInnovationWork;  
	@Column(name="d_establish_a_new_system")
	private BigDecimal dEstablishNewSystem;  
	@Column(name="d_talent_cultivate")
	private BigDecimal dTalentCultivate;  
	
	
	
	@Column(name="b_perform_duties")
	private BigDecimal bPerformDuties;  	
	@Column(name="b_innovation_work")
	private BigDecimal bInnovationWork;  
	@Column(name="b_establish_a_new_system")
	private BigDecimal bEstablishNewSystem;  
	@Column(name="b_talent_cultivate")
	private BigDecimal bTalentCultivate;  
	
}
