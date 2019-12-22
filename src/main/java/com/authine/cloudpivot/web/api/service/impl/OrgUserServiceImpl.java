package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.bean.OrgUser;
import com.authine.cloudpivot.web.api.mapper.OrgUserMapper;
import com.authine.cloudpivot.web.api.service.IOrgUserService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrgUserServiceImpl implements IOrgUserService {
    @Resource
    OrgUserMapper orgUserMapper;

    @Override
    public OrgUser getOrgUserByMobile(String mobile) {
        return orgUserMapper.getOrgUserByMobile(mobile);
    }

//    @Cacheable(cacheNames = {"orgUser"}, key = "#id")
    @Override
    public OrgUser getOrgUserById(String id) {
        return orgUserMapper.getOrgUserById(id);
    }
}
