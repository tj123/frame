package com.shundian.frame.controller;

import com.shundian.frame.api.sys.RoleService;
import com.shundian.frame.common.function.sys.RoleFunction;
import com.shundian.frame.model.sys.Role;
import com.shundian.lib.Page;
import com.shundian.lib.PageResult;
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
    private RoleService service;

    @RequestMapping
    public Result<?> list(Page page) {
        Result<PageResult<Role>> result = new Result<PageResult<Role>>();
        try {
            result.ok(service.list(page));
        } catch (Exception e) {
            result.error("错误", log, e);
        }
        return result;
    }



}
