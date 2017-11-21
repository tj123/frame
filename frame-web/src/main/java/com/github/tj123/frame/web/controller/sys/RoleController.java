package com.github.tj123.frame.web.controller.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.tj123.common.auth.annotation.Function;
import com.github.tj123.frame.api.common.PageRequest;
import com.github.tj123.frame.api.common.PageResponse;
import com.github.tj123.frame.api.service.FuncService;
import com.github.tj123.frame.api.service.RoleService;
import com.github.tj123.frame.web.common.unit.Role;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
@Function(Role.class)
@RequestMapping("/sys/role")
public class RoleController {

    @Reference
    RoleService service;

    @Reference
    FuncService funcService;

    @GetMapping
    public PageResponse<Map<String, Object>> list(@RequestParam Map<String, Object> map) throws Exception {
        return service.list(PageRequest.create(map));
    }

    @GetMapping("/allfn")
    public List<Map<String, Object>> allFunc() throws Exception {
        return funcService.all();
    }

}
