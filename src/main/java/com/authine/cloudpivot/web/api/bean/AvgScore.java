package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AvgScore {
    private String deptEffectId;
    private BigDecimal score;
}
