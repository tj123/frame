package com.shundian.frame.service.impl.sys;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.shundian.frame.api.sys.RoleService;
import com.shundian.frame.common.PageUtil;
import com.shundian.frame.envm.ProjectTypeEnum;
import com.shundian.frame.mapper.sys.RoleMapper;
import com.shundian.frame.model.sys.Role;
import com.shundian.lib.Page;
import com.shundian.lib.PageResult;
import com.shundian.lib.exception.CannotConvertException;
import com.shundian.lib.util.EnumUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper mapper;

    @Override
    public PageResult<Role> list(Page page) throws Exception {
        PageUtil<Role> util = new PageUtil<>();
        util.startPage(page);
        return util.assembleResult(mapper.selectAll());
    }

    @Override
    public Role list(String id) throws Exception {
        return null;
    }

}
