package com.shundian.frame.controller.sys;

import com.shundian.frame.api.dto.sys.RoleDto;
import com.shundian.frame.api.po.sys.RolePo;
import com.shundian.frame.api.service.sys.RoleService;
import com.shundian.frame.common.function.module.AddModule;
import com.shundian.frame.common.function.sys.RoleFunction;
import com.shundian.lib.Page;
import com.shundian.lib.PageResult;
import com.shundian.lib.Result;
import com.shundian.lib.common.bean.validate.impl.NotValidException;
import com.shundian.lib.function.Function;
import com.shundian.lib.function.Module;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/sys/role")
@Function(RoleFunction.class)
public class RoleController {

    @Autowired
    private RoleService service;

    @RequestMapping
    public Result<?> list(Page page) {
        Result<PageResult<RolePo>> result = new Result<PageResult<RolePo>>();
        try {
            result.ok(service.list(page));
        } catch (Exception e) {
            result.error("错误", log, e);
        }
        return result;
    }

    @RequestMapping("/add")
    @Module(AddModule.class)
    public Result<?> add(RoleDto dto) {
        Result<Object> result = new Result<Object>();
        try {
            service.add(dto.po());
            result.ok();
        } catch (NotValidException e){
            result.error(e);
        } catch (Exception e) {
            result.error("错误", log, e);
        }
        return result;
    }

    @RequestMapping("/addfunc")
    @Module(AddModule.class)
    public Result<?> addFunc(String role,@RequestParam("funcs[]") String[] funcs) {
        Result<Object> result = new Result<Object>();
        try {
            service.addFunc(role,funcs);
            result.ok();
        } catch (Exception e) {
            result.error("错误", log, e);
        }
        return result;
    }
    
    @RequestMapping("/mvfunc")
    @Module(AddModule.class)
    public Result<?> removeFunc(String role,@RequestParam("funcs[]") String[] funcs) {
        Result<Object> result = new Result<Object>();
        try {
            service.removeFunc(role,funcs);
            result.ok();
        } catch (Exception e) {
            result.error("错误", log, e);
        }
        return result;
    }
    
    @RequestMapping("/lstfunc")
    public Result<List<Map<String,Object>>> allFunction(String role,String name) {
        Result<List<Map<String,Object>>> result = new Result<List<Map<String,Object>>>();
        try {
            result.ok(service.listFunc(role,name));
        } catch (Exception e) {
            result.error("错误", log, e);
        }
        return result;
    }

}
