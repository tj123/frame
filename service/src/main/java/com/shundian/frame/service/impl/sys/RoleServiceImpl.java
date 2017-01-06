package com.shundian.frame.service.impl.sys;


import com.shundian.frame.api.po.sys.RoleFunctionModulePo;
import com.shundian.frame.api.po.sys.RolePo;
import com.shundian.frame.api.service.sys.FunctionService;
import com.shundian.frame.api.service.sys.RoleService;
import com.shundian.frame.common.PageUtil;
import com.shundian.frame.mapper.sys.FunctionModuleMapper;
import com.shundian.frame.mapper.sys.RoleFunctionModuleMapper;
import com.shundian.frame.mapper.sys.RoleMapper;
import com.shundian.lib.Page;
import com.shundian.lib.PageResult;
import com.shundian.lib.util.UuidUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper mapper;

    @Autowired
    private RoleFunctionModuleMapper roleFunctionModuleMapper;

    @Autowired
    private FunctionService functionService;

    @Autowired
    private FunctionModuleMapper functionModuleMapper;

    @Override
    public PageResult<RolePo> list(Page page) throws Exception {
        PageUtil<RolePo> util = new PageUtil<>();
        util.startPage(page);
        return util.assembleResult(mapper.selectAll());
    }

    @Override
    public RolePo list(String id) throws Exception {
        return null;
    }

    @Override
    public void add(RolePo rolePo) throws Exception {
        mapper.insert(rolePo);
    }

    @Override
    public void addFunc(String role, String[] funcs) throws Exception {
        List<RoleFunctionModulePo> funcModules = new ArrayList<>();
        if(funcs != null && funcs.length >0){
            for (String func : funcs) {
                RoleFunctionModulePo po = new RoleFunctionModulePo();
                po.setId(UuidUtil.getUUID());
                po.setFunctionModuleId(func);
                po.setRoleId(role);
                funcModules.add(po);
            }
        }
        roleFunctionModuleMapper.insertMulti(funcModules);
    }

    @Override
    public List<Map<String, Object>> listFunc(String role,String name) throws Exception {
        return functionService.checkList(functionModuleMapper.listFunc(role, name));
    }


}
