package com.shundian.frame.controller.sys;

import com.shundian.frame.api.service.sys.UserService;
import com.shundian.frame.common.function.module.ScanModule;
import com.shundian.frame.common.function.sys.UserFunction;
import com.shundian.lib.Page;
import com.shundian.lib.PageResult;
import com.shundian.lib.Result;
import com.shundian.lib.function.Function;
import com.shundian.lib.function.Module;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/sys/user")
@Function(UserFunction.class)
public class UserController {

    @Autowired
    private UserService service;


    @RequestMapping
    public Result<?> list(Page page) {
        Result<PageResult<Map<String, Object>>> result = new Result<PageResult<Map<String, Object>>>();
        try {
            result.ok(service.list(page));
        } catch (Exception e) {
            result.error("出错", log, e);
        }
        return result;
    }


    @RequestMapping("/scan")
    @Module(ScanModule.class)
    public Result<?> scan() {
        Result<?> res = new Result<Object>();

        return res.ok();
    }


}
