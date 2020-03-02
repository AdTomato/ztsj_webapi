package com.authine.cloudpivot.web.api.bean;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AnnualFinancialDetails {
    private String unit ;
    private String user_name ;
    private String expert_level ;
    @JsonAlias("work_achievement")
    private BigDecimal work_achievement ;
    private BigDecimal topic_research ;
    private BigDecimal study_train ;
    private BigDecimal echange_teach;
    private BigDecimal total_score ;
    private String u_id ;
    private String id ;

}
