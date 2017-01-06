package com.shundian.frame.controller.sys;

import com.shundian.frame.api.entity.sys.FunctionModule;
import com.shundian.frame.api.service.sys.FunctionService;
import com.shundian.frame.common.function.module.ScanModule;
import com.shundian.frame.common.function.sys.FuncFunction;
import com.shundian.lib.Page;
import com.shundian.lib.PageResult;
import com.shundian.lib.Result;
import com.shundian.lib.function.Function;
import com.shundian.lib.function.FunctionType;
import com.shundian.lib.function.Module;
import com.shundian.lib.function.ModuleType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/sys/func")
@Function(FuncFunction.class)
public class FunctionController {

    @Autowired
    private FunctionService functionService;

    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @RequestMapping
    public Result<?> list(Page page) {
        Result<PageResult<Map<String, Object>>> result = new Result<PageResult<Map<String, Object>>>();
        try {
            result.ok(functionService.list(page));
        } catch (Exception e) {
            result.error("错误", log, e);
        }
        return result;
    }

    @RequestMapping("/list")
    public Result<?> edit(String id) {
        Result<Map<String, Object>> result = new Result<Map<String, Object>>();
        try {
            result.ok(functionService.list(id));
        } catch (Exception e) {
            result.error("错误", log, e);
        }
        return result;
    }

    @RequestMapping("/list/all")
    public Result<List<Map<String,Object>>> allFunction(String name) {
        Result<List<Map<String,Object>>> result = new Result<List<Map<String,Object>>>();
        try {
            result.ok(functionService.listAll(name));
        } catch (Exception e) {
            result.error("错误", log, e);
        }
        return result;
    }

    @RequestMapping("/list_m")
    public Result<?> edit() {
        Result<List<FunctionModule>> result = new Result<List<FunctionModule>>();
        try {
            result.ok(functionService.findModules());
        } catch (Exception e) {
            result.error("错误", log, e);
        }
        return result;
    }

    @RequestMapping("/list_f")
    public Result<?> list_f() {
        Result<List<com.shundian.frame.api.entity.sys.Function>> result = new Result<List<com.shundian.frame.api.entity.sys.Function>>();
        try {
            result.ok(functionService.findFunctions());
        } catch (Exception e) {
            result.error("错误", log, e);
        }
        return result;
    }


    @RequestMapping("/scan")
    @Module(ScanModule.class)
    public Result<?> scan() {
        Result<?> res = new Result<Object>();
        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();
        Map<Class<? extends FunctionType<?>>, List<Class<? extends ModuleType>>> functions = new HashMap<Class<? extends FunctionType<?>>, List<Class<? extends ModuleType>>>();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : map.entrySet()) {
            Method method = entry.getValue().getMethod();
            Class<?> clazz = method.getDeclaringClass();
            Function methodFunction = method.getAnnotation(Function.class);
            Function classFunction = clazz.getAnnotation(Function.class);
            Function function = (methodFunction != null) ? methodFunction : classFunction;
            Module moduleMethod = method.getAnnotation(Module.class);
            if (function != null) {
                Class<? extends FunctionType<?>>[] functionClasses = function.value();
                Class<? extends ModuleType> moduleClass = function.module();
                if (moduleMethod != null) {
                    moduleClass = moduleMethod.value();
                }
                for (Class<? extends FunctionType<?>> functionClass : functionClasses) {
                    List<Class<? extends ModuleType>> modules = functions.get(functionClass);
                    if (modules == null) {
                        modules = new ArrayList<Class<? extends ModuleType>>();
                    }
                    if (!modules.contains(moduleClass)) {
                        modules.add(moduleClass);
                    }
                    functions.put(functionClass, modules);
                }
            } else if (moduleMethod != null) {
                String msg = "@Module 配置错误:不知道模块属于哪个功能!";
                log.error(msg, new Exception(msg));
                res.error(msg);
            }
        }
        try {
            functionService.insert(functions);
        } catch (Exception e) {
            log.debug("扫描出错", e);
            res.error(e.getMessage());
        }
        return res.ok();
    }


}
