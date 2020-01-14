package com.authine.cloudpivot.web.api.mapper;

import com.authine.cloudpivot.web.api.bean.OrgUser;

import java.util.Map;

public interface OrgUserMapper {

    /**
     * 根据手机号码获取用户信息
     * @param mobile
     *  手机号码
     * @return
     */
    public OrgUser getOrgUserByMobile(String mobile);

    /**
     * 根据用户id获取用户信息
     * @param id
     * 用户id
     * @return
     */
    public OrgUser getOrgUserById(String id);

    /**
     * 根据用户id更新密码
     * @param map
     */
    public void updatePasswordByUserId(Map map);

    /**
     * 根据用户id查询密码
     * @param userId
     * @return 用户密码
     */
    public String getPasswordByUserId(String userId);


}
