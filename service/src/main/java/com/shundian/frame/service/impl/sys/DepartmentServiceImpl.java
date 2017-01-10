package com.shundian.frame.service.impl.sys;


import com.shundian.frame.api.service.sys.DepartmentService;
import com.shundian.frame.common.PageUtil;
import com.shundian.frame.mapper.sys.DepartmentMapper;
import com.shundian.lib.Page;
import com.shundian.lib.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Slf4j
@Service
public class DepartmentServiceImpl implements DepartmentService {


    @Autowired
    private DepartmentMapper mapper;


    @Override
    public PageResult<Map<String, Object>> list(Page page) throws Exception {
        PageUtil<Map<String, Object>> pageUtil = new PageUtil<>();
        pageUtil.startPage(page);
        return pageUtil.assembleResult(mapper.list(page.assembleSearch()));
    }
}
