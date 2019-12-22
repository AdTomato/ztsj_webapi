package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

/**
 * 系统用户表
 */
@Data
public class OrgUser {

    /**
     * Id值，唯一
     */
    private String id;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 创建人
     */
    private String creater;

    /**
     * 是否删除
     */
    private byte deleted;

    /**
     * 扩展字段1
     */
    private String extend1;

    /**
     * 扩展字段2
     */
    private String extend2;

    /**
     * 扩展字段3
     */
    private String extend3;

    /**
     * 扩展字段4
     */
    private Integer extend4;

    /**
     * 扩展字段5
     */
    private Integer extend5;

    /**
     * 修改时间
     */
    private Date modifiedTime;

    /**
     * 修改人
     */
    private String modifier;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 是否激活
     */
    private Integer active;

    /**
     * 是否管理员
     */
    private Integer admin;

    /**
     * 称谓
     */
    private String appellation;

    /**
     * 出生日期
     */
    private Date birthday;

    /**
     * 是否企业老板
     */
    private Integer boss;

    /**
     * 部门id
     */
    private String departmentId;

    /**
     * 离职日期
     */
    private Date departureDate;

    /**
     * 钉钉id
     */
    private String dingtalkId;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 员工编号
     */
    private String employeeNo;

    /**
     * 员工职级
     */
    private Integer employeeRank;

    /**
     * 入职日期
     */
    private Date entryDate;

    /**
     * 性别
     */
    private String gender;

    /**
     * 身份证号
     */
    private String identityNo;

    /**
     * 头像url
     */
    private String imgUrl;

    /**
     * 是否部门主管
     */
    private Integer leader;

    /**
     * 上级主管id
     */
    private String managerId;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 员工姓名
     */
    private String name;

    /**
     * 办公电话
     */
    private String officePhone;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户名
     */
    private String username;

    /**
     * 隐私级别
     */
    private String privacyLevel;

    /**
     * 秘书id
     */
    private String secretaryId;

    /**
     * 排序字段
     */
    private BigInteger sortKey;

    /**
     * 来源id（钉钉unionid）
     */
    private String sourceId;

    /**
     * 状态（禁用，启用，锁定）
     */
    private String status;

    /**
     * 用户id（钉钉userid）
     */
    private String userId;

    /**
     * 姓名拼音
     */
    private String pinYin;

    /**
     * 姓名简拼
     */
    private String shortPinYin;

}
