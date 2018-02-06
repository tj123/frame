package com.github.tj123.frame.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.tj123.frame.api.common.PageRequest;
import com.github.tj123.frame.api.common.PageResponse;
import com.github.tj123.frame.api.pojo.vo.DictVo;
import com.github.tj123.frame.api.service.SDictService;
import com.github.tj123.frame.web.common.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/sdict")
public class SDictController {

    @Reference
    private SDictService service;

    @Autowired
    Session session;

    @PutMapping
    public void add(@RequestBody @Valid DictVo vo, BindingResult result) throws Exception {
        if (result.hasErrors()) {
            throw new BindException(result);
        }
        vo.setCreateById(session.getUserId());
        service.add(vo.getDto().toPo(), vo.getItems());
    }

    @DeleteMapping("/{id}")
    public void del(@PathVariable String id) throws Exception {
        service.del(id);
    }

    @PatchMapping
    public void edit(@RequestBody DictVo vo) throws Exception {
        if (vo.getId() == null || vo.getId().trim().equals("")) {
            throw new Exception("id 不能为空!");
        }
        service.edit(vo.getDto().toPo(),vo.getItems());
    }

    @GetMapping("/get/{id}")
    public Map<String, Object> get2(@PathVariable String id) throws Exception {
        return service.get2(id);
    }

    @GetMapping
    public PageResponse<Map<String, Object>> list(@RequestParam Map<String, Object> map) throws Exception {
        return service.list(PageRequest.create(map));
    }

}
