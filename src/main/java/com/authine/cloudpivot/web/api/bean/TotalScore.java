package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: wangyong
 * @Date: 2019/12/16 15:26
 * @Description:
 */
@Data
public class TotalScore {
    private String deptEffectId;
    private BigDecimal score;
    private String department;
}
