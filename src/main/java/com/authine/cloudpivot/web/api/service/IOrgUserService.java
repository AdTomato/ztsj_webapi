package com.authine.cloudpivot.web.api.service;

import com.authine.cloudpivot.web.api.bean.OrgUser;

public interface IOrgUserService {

    /**
     * 获取用户信息，根据手机号码
     * @param mobile 手机号码
     * @return
     */
    public OrgUser getOrgUserByMobile(String mobile);

    /**
     * 获取用户信息，根据用户id
     * @param id
     * @return
     */
    public OrgUser getOrgUserById(String id);

}
