package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FinalTotalResult {
    private String id;
    private BigDecimal annTrainexaminationScore;
    private BigDecimal deptTotal;
    private BigDecimal tutorTotal;
    private BigDecimal executiveTotal;
    private BigDecimal maindutyTotal;

}
