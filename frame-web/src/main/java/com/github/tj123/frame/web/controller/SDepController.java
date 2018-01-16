package com.github.tj123.frame.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.tj123.frame.api.common.PageRequest;
import com.github.tj123.frame.api.common.PageResponse;
import com.github.tj123.frame.api.pojo.dto.SDepDto;
import com.github.tj123.frame.api.pojo.po.SDepPo;
import com.github.tj123.frame.api.service.SDepService;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/sdep")
public class SDepController {

    @Reference
    private SDepService service;

    @PutMapping
    public void add(@Valid SDepDto dto,BindingResult result) throws Exception {
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
    public void edit(SDepDto dto) throws Exception {
        if (dto.getId() == null || dto.getId().trim().equals("")) {
            throw new Exception("id 不能为空!");
        }
        service.edit(dto.toPo());
    }

    @GetMapping("/get/{id}")
    public SDepPo get(@PathVariable String id) throws Exception {
        return service.get(id);
    }

    @GetMapping
    public PageResponse<Map<String, Object>> list(@RequestParam Map<String, Object> map) throws Exception {
        return service.list(PageRequest.create(map));
    }

}
