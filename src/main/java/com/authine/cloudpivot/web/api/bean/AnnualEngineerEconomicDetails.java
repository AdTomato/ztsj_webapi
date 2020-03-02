package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AnnualEngineerEconomicDetails {
    private String unit ;
    private String user_name ;
    private String expert_level ;
    private BigDecimal solve_problems;
    private BigDecimal construction_promotion;
    private BigDecimal special_events;
    private BigDecimal culture_teaching;
    private BigDecimal total_score ;
    private String u_id ;
    private String id ;
}
