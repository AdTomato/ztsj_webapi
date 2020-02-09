package com.authine.cloudpivot.web.api.service;

import com.authine.cloudpivot.web.api.bean.OrgUser;

import java.util.List;
import java.util.Map;

public interface IOrgUserService {

    /**
     * 获取用户信息，根据手机号码
     *
     * @param mobile 手机号码
     * @return
     */
    public OrgUser getOrgUserByMobile(String mobile);

    /**
     * 获取用户信息，根据用户id
     *
     * @param id
     * @return
     */
    public OrgUser getOrgUserById(String id);

    /**
     * 更改密码
     *
     * @param map
     */
    public void updatePasswordByUserId(Map map);

    /**
     * 根据用户id查询密码
     *
     * @param userId
     * @return
     */
    public String getPasswordByUserId(String userId);

    /**
     * 修改用户密码
     *
     * @param map
     */
    public void changePasswordByUserId(Map map);

    /**
     * 修改用户状态
     *
     * @param map
     */
    public void changeStatusByUserId(Map map);

    public List<String> getAllUserId();

    public String getRoleIdByName(String roleName);

}
