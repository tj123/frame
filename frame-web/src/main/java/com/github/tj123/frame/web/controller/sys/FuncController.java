package com.github.tj123.frame.web.controller.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.tj123.common.auth.AuthorizeSessionImpl;
import com.github.tj123.common.auth.annotation.Function;
import com.github.tj123.common.auth.annotation.Module;
import com.github.tj123.frame.api.common.PageRequest;
import com.github.tj123.frame.api.common.PageResponse;
import com.github.tj123.frame.api.service.FuncService;
import com.github.tj123.frame.web.common.unit.Func;
import com.github.tj123.frame.web.common.unit.Scan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@Function(Func.class)
@RequestMapping("/sys/func")
public class FuncController {

    @Reference
    FuncService service;

    @GetMapping
    public PageResponse<Map<String, Object>> list(@RequestParam Map<String, Object> map) throws Exception {
        return service.list(PageRequest.create(map));
    }

    @Autowired
    AuthorizeSessionImpl authorizeSession;

    @GetMapping("/scan")
    @Module(Scan.class)
    public void scan() throws Exception{
        service.scan(authorizeSession.scanList());
    }

}
