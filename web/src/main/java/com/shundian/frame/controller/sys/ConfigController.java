package com.shundian.frame.controller.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shundian.frame.api.service.sys.FunctionService;
import com.shundian.frame.api.common.function.module.ScanModule;
import com.shundian.frame.api.common.function.sys.ConfigFunction;
import com.shundian.lib.Result;
import com.shundian.lib.function.Function;
import com.shundian.lib.function.Module;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/sys/cfg")
@Function(ConfigFunction.class)
public class ConfigController {

    @Reference(version = "1.0.0")
    FunctionService functionService;


    @RequestMapping
    public Result<?> list() {
        Result<Object> result = new Result<Object>();


        return result;
    }


    @RequestMapping("/scan")
    @Module(ScanModule.class)
    public Result<?> scan() {
        Result<?> res = new Result<Object>();

        return res.ok();
    }


}
