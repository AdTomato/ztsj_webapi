package com.authine.cloudpivot.web.api.utils;

/**
 * @Author: wangyong
 * @Date: 2020-01-12 10:42
 * @Description: 用户工具类
 */
public class UserUtils {

    public static String getUserId(String userId) {
        if (null == userId) {
            return Points.ADMIN_ID;
        }
        return userId;
    }

}
