package com.shundian.frame.controller.sys;

import com.shundian.frame.api.service.sys.DepartmentService;
import com.shundian.frame.common.function.module.ScanModule;
import com.shundian.frame.common.function.module.SubmitModule;
import com.shundian.frame.common.function.sys.DepartmentFunction;
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
@RequestMapping("/sys/dep")
@Function(DepartmentFunction.class)
public class DepartmentController {

    @Autowired
    private DepartmentService service;

    @RequestMapping
    public Result<?> list(Page page) {
        Result<PageResult<Map<String, Object>>> res = new Result<PageResult<Map<String, Object>>>();
        try {
            res.ok(service.list(page));
        } catch (Exception e) {
            res.error(log, e);
        }
        return res;
    }


    @RequestMapping("/scan")
    @Module(ScanModule.class)
    public Result<?> scan() {
        Result<?> res = new Result<Object>();

        return res.ok();
    }

    @RequestMapping("/add")
    @Module(SubmitModule.class)
    public Result<?> submit() {
        Result<?> res = new Result<Object>();

        return res.ok();
    }


}
