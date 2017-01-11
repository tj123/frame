package com.shundian.frame.controller.sys;

import com.shundian.frame.common.function.module.ScanModule;
import com.shundian.frame.common.function.sys.LoginLogFunction;
import com.shundian.lib.Result;
import com.shundian.lib.function.Function;
import com.shundian.lib.function.Module;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/sys/log")
@Function(LoginLogFunction.class)
public class LoginLogController {

//    @RequestMapping
//    public Result<?> list(Page page) {
//        Result<Map<String,Object>> result = new Result<Map<String,Object>>();
//        try {
//            result.ok(menuService.list(page));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }


    @RequestMapping("/scan")
    @Module(ScanModule.class)
    public Result<?> scan() {
        Result<?> res = new Result<Object>();

        return res.ok();
    }


}
