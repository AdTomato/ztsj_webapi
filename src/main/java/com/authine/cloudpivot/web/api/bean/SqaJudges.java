package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

import java.util.List;

/**
 * @Author: wangyong
 * @Date: 2020-01-08 11:05
 * @Description: 发起定量考核里面的评委
 */
@Data
public class SqaJudges {

    public List<User> judge;
    public List<Department> department;

}
