package com.authine.cloudpivot.web.api.controller;


import com.authine.cloudpivot.engine.enums.ErrCode;
import com.authine.cloudpivot.web.api.bean.LeadPerson;
import com.authine.cloudpivot.web.api.controller.base.BaseController;

import com.authine.cloudpivot.web.api.service.LeadpersonService;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * 领导人员 前端控制器
 * </p>
 *
 * @author zsh
 * @since 2019-11-26
 */
@RestController
@RequestMapping("/ext/leadperson")
public class LeadpersonController extends BaseController {
    private Logger log = LoggerFactory.getLogger(LeadpersonController.class);

    @Autowired(required = true)
    private LeadpersonService LeadpersonService;

    /**
     * 领导人员展示界面,用部门id去查部门下面领导人员
     *
     * @param deptId
     * @return
     */
    @RequestMapping("/leadlist")
    public ResponseResult<List<LeadPerson>> users(String deptId) {
        List<LeadPerson> personList = new ArrayList<LeadPerson>();

        if (deptId == null || "".equals(deptId)) {
            return getErrResponseResult(personList, ErrCode.SYS_PARAMETER_EMPTY.getErrCode(), "error");

        }
        personList = LeadpersonService.getBydeptId(deptId);
        for (int i = 0; i < personList.size(); i++) {
            int j = i + 1;
            personList.get(i).setNum(j);
        }
        return getOkResponseResult(personList, "success");
    }
}