package com.authine.cloudpivot.web.api.controller;

import com.authine.cloudpivot.engine.enums.ErrCode;
import com.authine.cloudpivot.web.api.bean.OrgUser;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.service.IOrgUserService;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import jodd.util.BCrypt;
import org.apache.log4j.spi.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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

}
