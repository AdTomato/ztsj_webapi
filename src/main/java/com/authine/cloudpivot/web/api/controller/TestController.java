package com.authine.cloudpivot.web.api.controller;

import com.authine.cloudpivot.engine.api.model.organization.DepartmentModel;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: wangyong
 * @Date: 2020-01-08 14:19
 * @Description: 用于测试的接口
 */
@RestController
@RequestMapping("/ext/test")
@Slf4j
public class TestController extends BaseController {

    @RequestMapping("/testGetDepart")
    public void test() {
        String userId = getUserId();
        userId = "ff8080816e3e92fb016e3e9628ff00b4";
        List<DepartmentModel> list = getOrganizationFacade().getDepartmentsByUserId(userId);
    }

}
