package com.authine.cloudpivot.web.api.mapper;

import com.authine.cloudpivot.web.api.bean.OrgRoleUser;
import com.authine.cloudpivot.web.api.bean.OrgUser;

import java.util.List;
import java.util.Map;

public interface OrgUserMapper {

    /**
     * 根据手机号码获取用户信息
     *
     * @param mobile 手机号码
     * @return
     */
    public OrgUser getOrgUserByMobile(String mobile);

    /**
     * 根据用户id获取用户信息
     *
     * @param id 用户id
     * @return
     */
    public OrgUser getOrgUserById(String id);

    /**
     * 根据用户id更新密码
     *
     * @param map
     */
    public void updatePasswordByUserId(Map map);

    /**
     * 根据用户id查询密码
     *
     * @param userId
     * @return 用户密码
     */
    public String getPasswordByUserId(String userId);

    /**
     * 修改用户密码
     *
     * @param map
     */
    public void changePasswordByUserId(Map map);

    /**
     * 修改用户的状态
     *
     * @param map
     */
    public void changeStatusByUserId(Map map);

    /**
     * 获取所有用户的id
     *
     * @return
     */
    public List<String> getAllUserId();

    public String getRoleIdByName(String roleName);


    public void insertRoleUser(List<OrgRoleUser> roleUsers);

    public void deleteRoleUser(String roleId);

}
