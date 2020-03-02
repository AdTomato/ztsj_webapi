package com.authine.cloudpivot.web.api.bean;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.util.List;

@Data
public class ExpertsAssessScore {
    private String id;
    private String assessment_type;
    private String annual;
    private String assessment_content;
    private String work_unit;
    @JsonAlias("financial_audit_annual")
    private List<AnnualFinancialDetails> financial_audit_annual;
    private List<AnnualEngineerTechDetails> engineer_technology_annual;
    private List<AnnualEngineerEconomicDetails> engineer_economy_annual;
}
