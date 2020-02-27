package com.authine.cloudpivot.web.api.utils;

import com.authine.cloudpivot.engine.api.model.organization.DepartmentModel;
import com.authine.cloudpivot.engine.api.model.organization.UserModel;
import com.authine.cloudpivot.web.api.bean.BaseBean;
import com.authine.cloudpivot.web.api.constants.Constants;

import java.util.Date;
import java.util.UUID;

/**
 * @Author: wangyong
 * @Date: 2020-02-25 15:16
 * @Description: 系统字段数据设置工具类
 */
public class SystemDataSetUtils {

    public static void dataSet(String status,UserModel user, DepartmentModel department, String name, BaseBean baseBean) {
        baseBean.setId(UUID.randomUUID().toString().replace("-", ""));
        baseBean.setName(name);
        baseBean.setCreater(user.getId());
        baseBean.setCreatedDeptId(user.getDepartmentId());
        baseBean.setOwner(user.getId());
        baseBean.setOwnerDeptId(user.getDepartmentId());
        baseBean.setCreatedTime(new Date());
        baseBean.setModifier(user.getId());
        baseBean.setModifiedTime(new Date());
        baseBean.setSequenceStatus(status);
        baseBean.setOwnerDeptQueryCode(department.getQueryCode());
    }

}
