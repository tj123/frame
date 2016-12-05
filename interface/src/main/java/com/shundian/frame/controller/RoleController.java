package com.shundian.frame.controller;

import com.shundian.frame.api.sys.FunctionService;
import com.shundian.frame.common.function.sys.RoleFunction;
import com.shundian.lib.Result;
import com.shundian.lib.function.Function;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/sys/role")
@Function(RoleFunction.class)
public class RoleController {

    @Autowired
    FunctionService functionService;

    @RequestMapping
    public Result<?> list(){
        Result<Object> result = new Result<Object>();


        return result;
    }


    @RequestMapping("/scan")
    public Result<?> scan(){
        return null;
    }


}
