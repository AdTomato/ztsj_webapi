package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

import java.time.DateTimeException;
import java.util.Date;

/**
 * @Author: wangyong
 * @Date: 2020-02-19 11:39
 * @Description: 角色用户表
 */
@Data
public class OrgRoleUser {

    private String id;
    private Date createdTime;
    private String creater;
    private Boolean deleted;
    private String extend1;
    private String extend2;
    private String extend3;
    private Integer extend4;
    private Integer extend5;
    private Date modifiedTime;
    private String modifier;
    private String remarks;
    private String ouScope;
    private String roleId;
    private String userId;
    private String userSourceId;
    private String roleSourceId;
    private String unitType;
    private String deptId;


}
