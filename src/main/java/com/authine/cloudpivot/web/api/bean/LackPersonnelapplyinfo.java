package com.authine.cloudpivot.web.api.bean;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class LackPersonnelapplyinfo {
    private String id ;
    private String applyId;
    private BigDecimal mainduty_learning;
    private BigDecimal mainduty_pro;
    private BigDecimal mainduty_post;
    private BigDecimal mainduty_performduty;
    private BigDecimal mainduty_total;
    private String userId;


}
