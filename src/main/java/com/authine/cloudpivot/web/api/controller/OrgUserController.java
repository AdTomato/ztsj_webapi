package com.authine.cloudpivot.web.api.controller;

import com.authine.cloudpivot.engine.api.facade.OrganizationFacade;
import com.authine.cloudpivot.engine.api.model.organization.RoleUserModel;
import com.authine.cloudpivot.engine.api.model.organization.UserModel;
import com.authine.cloudpivot.engine.enums.ErrCode;
import com.authine.cloudpivot.engine.enums.status.UserStatus;
import com.authine.cloudpivot.engine.enums.type.UnitType;
import com.authine.cloudpivot.web.api.bean.OrgUser;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.service.IOrgUserService;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import com.dingtalk.api.request.OapiCallRemoveuserlistRequest;
import jodd.util.BCrypt;
import org.apache.log4j.spi.ErrorCode;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.annotation.ResponseStatusExceptionResolver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ext/orgUser")
public class OrgUserController extends BaseController {

    @Autowired
    IOrgUserService orgUserService;

    @RequestMapping("/getOrgUserNameByMobile")
    public ResponseResult<String> getOrgUserNameByMobile(String mobile) {
        if (mobile == null) {
            return getErrResponseResult("mobile为空", ErrCode.SYS_PARAMETER_EMPTY.getErrCode(), "error");
        }
        OrgUser orgUser = orgUserService.getOrgUserByMobile(mobile);
        if (orgUser == null) {
            return getErrResponseResult(mobile, ErrCode.ORG_USER_ACCOUNT_UNIQUE.getErrCode(), ErrCode.ORG_USER_ACCOUNT_UNIQUE.getErrMsg());
        }
        return getOkResponseResult(orgUser.getUsername(), "success");
    }

    @RequestMapping("/getOrgUserNameById")
    public OrgUser getOrgUserNameById(String id) {
        OrgUser orgUser = orgUserService.getOrgUserById(id);
        return orgUser;
    }

    @PutMapping("/updatePassword")
    public ResponseResult<Void> updatePasswordById(String oldPassword, String newPassword) {
        String userId = getUserId();
        if (userId == null) {
            return getErrResponseResult(404L, "异常");
        }

        String oldPwd = orgUserService.getPasswordByUserId(userId);
        oldPwd = oldPwd.substring(8);
        if (!BCrypt.checkpw(oldPassword, oldPwd)) {
            return getErrResponseResult(ErrCode.SYS_PASSWORD_ERROR.getErrCode(), "密码错误");
        }

        String pwd = BCrypt.hashpw(newPassword, BCrypt.gensalt());
//        BCrypt.checkpw()
        pwd = "{bcrypt}" + pwd;
        Map map = new HashMap();
        map.put("userId", userId);
        map.put("password", pwd);
        orgUserService.updatePasswordByUserId(map);
        return getErrResponseResult(ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }

    @PutMapping("/changePassword")
    public ResponseResult<Void> changePasswordByUserId(@RequestParam String userId, @RequestParam String password) {
        String pwd = BCrypt.hashpw(password, BCrypt.gensalt());
        Map map = new HashMap();
        map.put("userId", userId);
        map.put("password", "{bcrypt}" + pwd);
        orgUserService.changePasswordByUserId(map);
        return getErrResponseResult(ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }

    @PostMapping("/addUser")
    public ResponseResult<String> addUser(@RequestBody OrgUser orgUser) {

        if (null == getUserId()) {
            return getErrResponseResult("error", 404L, "error");
        }

        OrganizationFacade organizationFacade = getOrganizationFacade();
        String userId = "";
        UserModel userModel = null;
        if (null != orgUser.getId()) {
            // 更新
        } else {
            // 创建
            String hashpw = BCrypt.hashpw(orgUser.getPassword(), BCrypt.gensalt());
            orgUser.setPassword("{bcrypt}" + hashpw);
            userModel = setUserModel(orgUser);
            userModel = organizationFacade.addUser(userModel);
        }
        return getErrResponseResult(userModel.getId(), ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }

    @PutMapping("/changeStatus")
    public ResponseResult<String> changeStatusByUserId(@RequestParam String id, @RequestParam String status) {
        if (null == getUserId()) {
            return getErrResponseResult("error", 404L, "error");
        }
        Map map = new HashMap();
        map.put("userId", id);
        if ("ENABLE".equals(status)) {
            // 启用
            map.put("status", "ENABLE");
        } else if ("DISABLE".equals(status)) {
            // 禁用
            map.put("status", "DISABLE");
        } else {
            return getErrResponseResult("error", 404L, "error");
        }

        return getErrResponseResult("success", ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }

    @PostMapping("/addAllUser")
    public ResponseResult<String> addAllUser(@RequestParam String roleName) {
        String roleId = orgUserService.getRoleIdByName(roleName);
        if (null == roleId) {
            return getErrResponseResult("失败", 404L, "获取角色id失败");
        }
        List<String> userIds = orgUserService.getAllUserId();
        OrganizationFacade organizationFacade = getOrganizationFacade();
        for (String userId :
                userIds) {
            RoleUserModel roleUserModel = new RoleUserModel();
            roleUserModel.setRoleId(roleId);
            roleUserModel.setUserId(userId);
            roleUserModel.setUnitType(UnitType.USER);
            organizationFacade.addRoleUser(roleUserModel);
        }
        return getErrResponseResult("成功", ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }

    /**
     * @param orgUser : 前端传过来的用户信息
     * @return : com.authine.cloudpivot.engine.api.model.organization.UserModel
     * @Author: wangyong
     * @Date: 2020/1/15 23:38
     * @Description: 将前端传过来的用户信息封装成UserModel
     */
    private UserModel setUserModel(OrgUser orgUser) {
        UserModel userModel = new UserModel();
        userModel.setDepartmentId(orgUser.getDepartmentId());
        userModel.setAdmin(false);
        userModel.setActive(true);
        userModel.setUsername(orgUser.getUsername());
        userModel.setName(orgUser.getName());
        userModel.setStatus(UserStatus.ENABLE);
        userModel.setLeader(false);
        userModel.setPassword(orgUser.getPassword());
        userModel.setBoss(false);
        userModel.setPinYin(orgUser.getPinYin());
        userModel.setShortPinYin(orgUser.getShortPinYin());
        return userModel;
    }
}
