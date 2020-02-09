package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Table(name = "i6rlc_expert_assessment_detail")
public class ExpertAssessmentDetail {


    @Id
    private String id;
    @Column(name = "expert_office_assessment_id")
    private String sId;  //关联专人任期考核评分表
    @Column(name = "expert_term_assessment_id")
    private String pId;  //关联专家任期考核


    @Column(name = "assessment_content")
    private String assessmentContent;  //考核主体

    @Column(name = "perform_duties")
    private BigDecimal PerformDuties;
    @Column(name = "innovation_work")
    private BigDecimal InnovationWork;
    @Column(name = "establish_a_new_system")
    private BigDecimal EstablishNewSystem;
    @Column(name = "talent_cultivate")
    private BigDecimal TalentCultivate;

}
