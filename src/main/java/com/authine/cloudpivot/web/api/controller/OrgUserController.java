package com.authine.cloudpivot.web.api.controller;

import com.authine.cloudpivot.engine.api.facade.BizObjectFacade;
import com.authine.cloudpivot.engine.enums.ErrCode;
import com.authine.cloudpivot.web.api.bean.OrgUser;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.service.IOrgUserService;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ext/orgUser")
public class OrgUserController extends BaseController {

//    @Autowired
//    IOrgUserService orgUserService;
//
//    @RequestMapping("/getOrgUserNameByMobile")
//    public ResponseResult<String> getOrgUserNameByMobile(String mobile) {
//        if (mobile == null) {
//            return getErrResponseResult("mobile为空", ErrCode.SYS_PARAMETER_EMPTY.getErrCode(), "error");
//        }
//        OrgUser orgUser = orgUserService.getOrgUserByMobile(mobile);
//        if (orgUser == null) {
//            return getErrResponseResult(mobile, ErrCode.ORG_USER_ACCOUNT_UNIQUE.getErrCode(), ErrCode.ORG_USER_ACCOUNT_UNIQUE.getErrMsg());
//        }
//        return getOkResponseResult(orgUser.getUsername(), "success");
//    }
//    @RequestMapping("/getOrgUserNameById")
//    public OrgUser getOrgUserNameById(String id) {
//        OrgUser orgUser = orgUserService.getOrgUserById(id);
//        return orgUser;
//    }

}
