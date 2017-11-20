package com.github.tj123.frame.web.controller.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.tj123.common.auth.AuthorizeType;
import com.github.tj123.common.auth.UnitModals;
import com.github.tj123.common.auth.annotation.Authorize;
import com.github.tj123.common.auth.annotation.Function;
import com.github.tj123.common.auth.db.DbModals;
import com.github.tj123.frame.api.common.PageRequest;
import com.github.tj123.frame.api.common.PageResponse;
import com.github.tj123.frame.api.service.FuncService;
import com.github.tj123.frame.web.common.unit.Func;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Map;

/**
 * Created by TJ on 2017/10/24. */
@Authorize(AuthorizeType.ALL)
@Function(Func.class)
@RestController
@RequestMapping("/sys/func")
public class FuncController {

    @Reference
    FuncService service;

    @GetMapping
    public PageResponse<Map<String, Object>> list(@RequestParam Map<String, Object> map) throws Exception {
        return service.list(PageRequest.create(map));
    }

    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @GetMapping("/scan")
    public void scan() throws Exception{
        service.scan(new DbModals(UnitModals.scan(requestMappingHandlerMapping)).toListMap());
    }

    @GetMapping("/scand")
    public DbModals scanData() throws Exception {
        UnitModals unitModals = UnitModals.scan(requestMappingHandlerMapping);
        return new DbModals(unitModals);
    }

}
