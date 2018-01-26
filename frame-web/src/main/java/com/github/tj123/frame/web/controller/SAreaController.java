package com.github.tj123.frame.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.tj123.common.auth.annotation.Function;
import com.github.tj123.frame.api.common.PageRequest;
import com.github.tj123.frame.api.common.PageResponse;
import com.github.tj123.frame.api.common.utils.AreaUtils;
import com.github.tj123.frame.api.pojo.dto.SAreaDto;
import com.github.tj123.frame.api.service.SAreaService;
import com.github.tj123.frame.web.common.Session;
import com.github.tj123.frame.web.common.unit.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;


@RestController
@Function(Area.class)
@RequestMapping("/sarea")
public class SAreaController {

    @Reference
    private SAreaService service;

    @Autowired
    Session session;

    @PutMapping
    public void add(@Valid SAreaDto dto, BindingResult result) throws Exception {
        if (result.hasErrors()) {
            throw new BindException(result);
        }
        service.add(dto.toPo());
    }

    @DeleteMapping("/{id}")
    public void del(@PathVariable String id) throws Exception {
        service.del(id);
    }

    @PatchMapping
    public void edit(SAreaDto dto) throws Exception {
        if (dto.getAreaId() == null || dto.getAreaId().trim().equals("")) {
            throw new Exception("id 不能为空!");
        }
        service.edit(dto.toPo());
    }

    @GetMapping("/get/{id}")
    public Map<String, Object> get2(@PathVariable String id) throws Exception {
        return service.get2(id);
    }

    @GetMapping
    public PageResponse<Map<String, Object>> list(@RequestParam Map<String, Object> map) throws Exception {
        map.put("areaId", AreaUtils.simple(session.getAreaId()));
        return service.list(PageRequest.create(map));
    }

}
