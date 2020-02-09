package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.bean.OrgUser;
import com.authine.cloudpivot.web.api.mapper.OrgUserMapper;
import com.authine.cloudpivot.web.api.service.IOrgUserService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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

    @Override
    public void updatePasswordByUserId(Map map) {
        orgUserMapper.updatePasswordByUserId(map);
    }

    @Override
    public String getPasswordByUserId(String userId) {
        return orgUserMapper.getPasswordByUserId(userId);
    }

    @Override
    public void changePasswordByUserId(Map map) {
        orgUserMapper.changePasswordByUserId(map);
    }

    @Override
    public void changeStatusByUserId(Map map) {
        orgUserMapper.changeStatusByUserId(map);
    }

    @Override
    public List<String> getAllUserId() {
        return orgUserMapper.getAllUserId();
    }

    @Override
    public String getRoleIdByName(String roleName) {
        return orgUserMapper.getRoleIdByName(roleName);
    }
}
