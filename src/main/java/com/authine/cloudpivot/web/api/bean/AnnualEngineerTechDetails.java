package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AnnualEngineerTechDetails {
    private String unit ;
    private String user_name ;
    private String expert_level ;
    private BigDecimal review_discussion;
    private BigDecimal poser_field_service;
    private BigDecimal train_teaching;
    private BigDecimal technology_innovate;
    private BigDecimal total_score ;
    private String u_id ;
    private String id ;
}
