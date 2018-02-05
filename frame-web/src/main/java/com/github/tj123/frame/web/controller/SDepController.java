package com.github.tj123.frame.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.tj123.common.auth.annotation.Function;
import com.github.tj123.common.auth.annotation.Module;
import com.github.tj123.frame.api.common.PageRequest;
import com.github.tj123.frame.api.common.PageResponse;
import com.github.tj123.frame.api.pojo.dto.SDepDto;
import com.github.tj123.frame.api.service.RoleService;
import com.github.tj123.frame.api.service.SDepService;
import com.github.tj123.frame.web.common.Session;
import com.github.tj123.frame.web.common.unit.Dep;
import com.github.tj123.frame.web.common.unit.module.Add;
import com.github.tj123.frame.web.common.unit.module.Edit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@Function(Dep.class)
@RequestMapping("/sdep")
public class SDepController {

    @Reference
    private SDepService service;

    @Autowired
    Session session;

    @Reference
    RoleService roleService;

    @PutMapping
    @Module(Add.class)
    public void add(@Valid SDepDto dto, @RequestParam("roles[]") List<String> roles,
                    BindingResult result) throws Exception {
        if (result.hasErrors()) {
            throw new BindException(result);
        }
        dto.setCreateById(session.getUserId());
        service.add(dto.toPo(), roles);
    }

    @DeleteMapping("/{id}")
    public void del(@PathVariable String id) throws Exception {
        service.del(id);
    }

    @PatchMapping
    @Module(Edit.class)
    public void edit(SDepDto dto, @RequestParam("roles[]") List<String> roles) throws Exception {
        if (dto.getId() == null || dto.getId().trim().equals("")) {
            throw new Exception("id 不能为空!");
        }
        service.edit(dto.toPo(), roles);
    }

    @GetMapping("/get/{id}")
    public Map<String, Object> get(@PathVariable String id) throws Exception {
        return service.get(id);
    }

    @GetMapping
    public PageResponse<Map<String, Object>> list(@RequestParam Map<String, Object> map) throws Exception {
        if (!map.containsKey("area")) {
            map.put("area", session.getAreaId());
        }
        map.put("areaId", map.get("area"));
        map.remove("area");
        return service.list(PageRequest.create(map));
    }

    @GetMapping("/areas")
    public List<Map<String, Object>> areas() throws Exception {
        return service.areas(session.getAreaId());
    }

    @GetMapping("/arls")
    public List<Map<String, Object>> allRoles() throws Exception {
        return roleService.allRoles(session.getDepId(),session.getUserId());
    }


}
