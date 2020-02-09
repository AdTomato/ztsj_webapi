package com.authine.cloudpivot.web.api.controller;

import com.authine.cloudpivot.web.api.service.IPermAdmin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wangyong
 * @Date: 2019-12-24 14:38
 * @Description: 返回管理员数据的控制层
 */
@RestController
@RequestMapping("/ext/permAdmin")
@Slf4j
public class PermAdminController {

    @Autowired
    IPermAdmin permAdmin;

    /**
     * @param userId : 用户id
     * @return : boolean
     * @Author: wangyong
     * @Date: 2019/12/24 14:43
     * @Description: 根据传过来的userId判断该userId是否是管理员
     */
    @GetMapping("/isAdmin")
    public boolean isAdmin(String userId) {
        log.info("获取userId值为:" + userId);
        String adminId = permAdmin.getPermAdminIdByUserId(userId);
        if (adminId != null) {
            return true;
        }
        return false;
    }

}
