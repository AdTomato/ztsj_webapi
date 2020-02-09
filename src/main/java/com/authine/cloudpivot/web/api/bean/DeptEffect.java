package com.authine.cloudpivot.web.api.bean;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class DeptEffect {
    @JsonAlias("dept_name")
    private List<DeptName> deptNameList;
    @JsonAlias("basic_score")
    private BigDecimal basicScore;
    @JsonAlias("handle_score")
    private BigDecimal handleScore;
    @JsonAlias("workarea_deduct")
    private BigDecimal workareaDeduct;
    @JsonAlias("officialcontent_deduct")
    private BigDecimal officialcontentDeduct;
    @JsonAlias("handle_deduct")
    private BigDecimal handleDeduct;
    @JsonAlias("total_score")
    private BigDecimal totalScore;
    private String id;

}
