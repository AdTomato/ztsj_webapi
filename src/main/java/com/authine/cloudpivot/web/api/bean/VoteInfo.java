package com.authine.cloudpivot.web.api.bean;

import lombok.Data;
import lombok.Generated;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@Data
public class VoteInfo {

    private int id;
    private int assessmentYear;
    private String assessmentSeason;
    private String deptNameId;
    private BigDecimal basicScore;
    private BigDecimal handleScore;
    private BigDecimal workareaDeduct;
    private BigDecimal officialcontentDeduct;
    private BigDecimal handleDeduct;
    private BigDecimal totalScore;
    private String deptEffectId;
    private String userId;
    private String userName;
    private String deptName;
    private String parentId;
}
