package com.github.tj123.frame.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.tj123.common.auth.annotation.Function;
import com.github.tj123.common.auth.annotation.Module;
import com.github.tj123.frame.api.common.PageRequest;
import com.github.tj123.frame.api.common.PageResponse;
import com.github.tj123.frame.api.common.exception.MessageException;
import com.github.tj123.frame.api.pojo.dto.SUserDto;
import com.github.tj123.frame.api.service.SDepService;
import com.github.tj123.frame.api.service.SUserService;
import com.github.tj123.frame.web.common.Session;
import com.github.tj123.frame.web.common.unit.Func;
import com.github.tj123.frame.web.common.unit.User;
import com.github.tj123.frame.web.common.unit.module.Add;
import com.github.tj123.frame.web.common.unit.module.Del;
import com.github.tj123.frame.web.common.unit.module.Edit;
import com.github.tj123.frame.web.common.unit.module.PassowrdEdit;
import com.github.tj123.frame.web.config.ProjectProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@Function(User.class)
@RequestMapping("/suser")
public class SUserController {

    @Reference
    private SUserService service;

    @Reference
    private SDepService depService;

    @Autowired
    Session session;

    @Autowired
    ProjectProperties projectProperties;


    @PutMapping
    @Module({Add.class, Func.class})
    public void add(@Valid SUserDto dto, @RequestParam("roles[]") List<String> roles, BindingResult result) throws Exception {
        if (result.hasErrors()) {
            throw new BindException(result);
        }
        service.add(dto.toPo(), roles);
    }

    @Module(Del.class)
    @DeleteMapping("/{id}")
    public void del(@PathVariable String id) throws Exception {
        service.del(id);
    }

    @Module(Edit.class)
    @PatchMapping
    public void edit(SUserDto dto, @RequestParam("roles[]") List<String> roles) throws Exception {
        if (dto.getId() == null || dto.getId().trim().equals("")) {
            throw new Exception("id 不能为空!");
        }
        service.edit(dto.toPo(), roles);
    }

    @PatchMapping("/pwd")
    @Module(PassowrdEdit.class)
    public void changePwd(String password, String userId) throws Exception {
        if (password == null || userId == null) {
            throw new MessageException("密码不能为空");
        }
        service.changePassword(userId, password);
    }

    @GetMapping("/get/{id}")
    public Map<String, Object> get2(@PathVariable String id) throws Exception {
        return service.get2(id);
    }

    @GetMapping
    public PageResponse<Map<String, Object>> list(@RequestParam Map<String, Object> map) throws Exception {
        map.put("areaId", session.getAreaId());
        return service.list(PageRequest.create(map));
    }

    @GetMapping("/exist")
    public Boolean isExist(String username) throws Exception {
        return service.isExist(username);
    }

    @GetMapping("/dep/search")
    public List<Map<String, Object>> searchDep(String name) throws Exception {
        return depService.search(name);
    }

    @GetMapping("/dep/get")
    public Map<String, Object> getDep(String id) throws Exception {
        return depService.get(id);
    }

    @PostMapping("/quit")
    public void quit(HttpSession session) throws Exception {
        session.invalidate();
    }

    @GetMapping("/session")
    public Map<String, Object> session() {
        Map<String, Object> map = session.toMap();
        map.put("version", projectProperties.getVersion());
        return map;
    }


}
