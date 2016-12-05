package com.shundian.frame.controller;

import com.shundian.frame.api.sys.FunctionService;
import com.shundian.frame.common.function.module.ScanModule;
import com.shundian.frame.common.function.sys.FuncFunction;
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
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/sys/func")
@Function(FuncFunction.class)
public class FunctionController {

    @Autowired
    FunctionService functionService;

    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    public Result<?> list(){
        Result<Object> result = new Result<Object>();


        return result;
    }


    @RequestMapping("/scan")
    @Module(ScanModule.class)
    public Result<?> scan(){
        Result<?> res = new Result<Object>();
        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : map.entrySet()) {
            Method method = entry.getValue().getMethod();
            Class<?> clazz = method.getDeclaringClass();
            Function methodFunction = method.getAnnotation(Function.class);
            Function classFunction = clazz.getAnnotation(Function.class);
            Function function = (methodFunction != null) ? methodFunction :classFunction;
            Module moduleMethod = method.getAnnotation(Module.class);
            if(function != null){
                Class<? extends FunctionType<?>>[] functionClasses = function.value();
                Class<? extends ModuleType> moduleClass = function.module();
                if(moduleMethod != null){
                    moduleClass = moduleMethod.value();
                }
                for(Class<? extends FunctionType<?>> functionClass:functionClasses){
                    try {
                        functionService.insertUpdateModule(functionClass,moduleClass);
                    } catch (Exception e) {
                        log.debug("扫描出错",e);
                    }
                }
            }else if(moduleMethod != null){
                log.error("@Module 配置错误:不知道模块属于哪个功能!",new Exception("@Module 配置错误:不知道模块属于哪个功能!"));
            }
        }
        return res.ok();
    }


}
