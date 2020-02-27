package com.authine.cloudpivot.web.api.utils;

import org.thymeleaf.util.StringUtils;

import java.math.RoundingMode;
import java.text.NumberFormat;

/**
 * @Author: wangyong
 * @Date: 2020-01-10 16:29
 * @Description: double工具类
 */
public class DoubleUtils {

    /**
     * @param d     : 需要四舍五入的数值
     * @param digit : 保留小数点后的位数
     * @return : java.lang.Double
     * @Author: wangyong
     * @Date: 2020/1/10 16:31
     * @Description: 四舍五入
     */
    public static Double doubleRound(Double d, Integer digit) {
        NumberFormat nf = NumberFormat.getNumberInstance();
        // 保留两位小数
        nf.setMaximumFractionDigits(digit);
        // 如果不需要四舍五入，可以使用RoundingMode.DOWN
        nf.setRoundingMode(RoundingMode.HALF_EVEN);
        if (null == d) {
            return 0D;
        } else {
            return Double.parseDouble(nf.format(d));
        }
    }

    /**
     * @param d : 需要转换的double
     * @return : java.lang.Double
     * @Author: wangyong
     * @Date: 2020/1/18 20:57
     * @Description: 将null转换成0
     */
    public static Double nullToDouble(Double d) {
        if (null == d) {
            return 0D;
        } else {
            return d;
        }
    }

    public static Double stringToDouble(String s) {
        if (StringUtils.isEmpty(s)) {
            return 0D;
        } else {
            return Double.parseDouble(s);
        }
    }

}
