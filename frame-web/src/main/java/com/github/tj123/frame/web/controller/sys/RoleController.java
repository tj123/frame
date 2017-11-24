package com.github.tj123.frame.web.controller.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.tj123.common.auth.annotation.Function;
import com.github.tj123.frame.api.common.PageRequest;
import com.github.tj123.frame.api.common.PageResponse;
import com.github.tj123.frame.api.dto.sys.RoleDto;
import com.github.tj123.frame.api.po.sys.RolePo;
import com.github.tj123.frame.api.service.FuncService;
import com.github.tj123.frame.api.service.RoleService;
import com.github.tj123.frame.web.common.Session;
import com.github.tj123.frame.web.common.unit.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;


@RestController
@Function(Role.class)
@RequestMapping("/sys/role")
public class RoleController {

    @Autowired
    Session session;

    @Reference
    RoleService service;

    @Reference
    FuncService funcService;

    @GetMapping
    public PageResponse<Map<String, Object>> list(@RequestParam Map<String, Object> map) throws Exception {
        return service.list(PageRequest.create(map));
    }

    @PostMapping("/add")
    public void add(RoleDto dto, @RequestParam("roles[]") List<String> roles) throws Exception {
        RolePo po = dto.toPo();
        po.setCreateTime(new Date());
        po.setCreateById(session.getUserId());
        service.add(po, roles);
    }

    @GetMapping("/allfn")
    public List<Map<String, Object>> allFunc() throws Exception {
        return funcService.all();
    }

}
