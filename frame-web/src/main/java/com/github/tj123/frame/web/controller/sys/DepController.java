package com.github.tj123.frame.web.controller.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.tj123.common.auth.annotation.Function;
import com.github.tj123.frame.api.common.PageRequest;
import com.github.tj123.frame.api.common.PageResponse;
import com.github.tj123.frame.api.service.DepService;
import com.github.tj123.frame.api.service.RoleService;
import com.github.tj123.frame.web.common.Session;
import com.github.tj123.frame.web.common.unit.Dep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

//@Authorize(AuthorizeType.ALL)
@Function(Dep.class)
@RestController
@RequestMapping("/sys/dep")
public class DepController {

    @Autowired
    Session session;

    @Reference
    DepService service;

    @Reference
    RoleService roleService;

    @GetMapping
    public PageResponse<Map<String, Object>> list(@RequestParam Map<String, Object> map) throws Exception {
        return service.list(PageRequest.create(map));
    }

    @GetMapping("/areas")
    public List<Map<String,Object>> areas() throws Exception {
        return service.areas(session.getAreaId());
    }

    @GetMapping("/arls")
    public List<Map<String,Object>> allRoles() throws Exception {
        return roleService.allRoles();
    }


}
