package com.authine.cloudpivot.web.api.utils;

/**
 * @Author: wangyong
 * @Date: 2020-01-12 12:48
 * @Description: 流程状态工具类
 */
public class SequenceStatusUtils {

    /**
     * @param sequenceStatus : 流程状态
     * @return : boolean
     * @Author: wangyong
     * @Date: 2020/1/12 12:50
     * @Description: 流程是否已经结束
     */
    public static boolean isCompleted(String sequenceStatus) {
        return Points.COMPLETED.equals(sequenceStatus);
    }

}
