package com.authine.cloudpivot.ext.controller;

import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.handler.CustomizedOrigin;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import com.authine.cloudpivot.web.api.view.license.LicenseNotify;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Random;

/**
 * 这是测试类
 *
 * @author DELL
 */
@Api(value = "扩展测试接口", tags = "其他::扩展测试接口")
@Controller
@RequestMapping("/api/licenseExt")
public class LicenseExtController extends BaseController {

    @GetMapping("/notify1")
    @ResponseBody
    @CustomizedOrigin(level = 2)
    public ResponseResult<LicenseNotify> notify1() {
        LicenseNotify notify = new LicenseNotify();
        notify.setRemainingDays(new Random().nextInt(1000));
        return getOkResponseResult(notify, "success ext1");
    }

    @GetMapping("/notify2")
    @ResponseBody
    @CustomizedOrigin(level = 5)
    public ResponseResult<LicenseNotify> notify2() {
        LicenseNotify notify = new LicenseNotify();
        notify.setRemainingDays(new Random().nextInt(1000));
        return getOkResponseResult(notify, "success ext2");
    }

    @GetMapping("/notifyExpired/{id}")
    @ResponseBody
    public ResponseResult<String> testNotifyExpired(@PathVariable("id") String id) {
        return getOkResponseResult(id, "testNotifyExpired");
    }

    @PostMapping("/postTest")
    @ResponseBody
    public ResponseResult<Map> postTest(@RequestBody Map<String, String> params) {
        return getOkResponseResult(params, "postTest");
    }
}
