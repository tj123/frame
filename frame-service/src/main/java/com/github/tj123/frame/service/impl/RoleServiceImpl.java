package com.github.tj123.frame.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.tj123.frame.api.common.PageRequest;
import com.github.tj123.frame.api.common.PageResponse;
import com.github.tj123.frame.api.common.utils.CompareUtils;
import com.github.tj123.frame.api.common.utils.UuidUtils;
import com.github.tj123.frame.api.po.sys.RoleFuncPo;
import com.github.tj123.frame.api.po.sys.RolePo;
import com.github.tj123.frame.api.service.RoleService;
import com.github.tj123.frame.service.common.PageUtils;
import com.github.tj123.frame.service.mapper.RoleFuncMapper;
import com.github.tj123.frame.service.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper mapper;

    @Autowired
    RoleFuncMapper funcMapper;


    @Override
    public PageResponse<Map<String, Object>> list(PageRequest request) throws Exception {
        return PageUtils.query(request, () -> mapper.list());
    }

    @Override
    @Transactional
    public void add(RolePo po, List<String> funs) throws Exception {
        String uuid = UuidUtils.getUUID();
        po.setId(uuid);
        List<RoleFuncPo> list = new ArrayList<>();
        if (funs != null && !funs.isEmpty()) {
            for (String role : funs) {
                RoleFuncPo funcPo = new RoleFuncPo();
                funcPo.setId(UuidUtils.getUUID());
                funcPo.setRoleId(uuid);
                funcPo.setFuncId(role);
                list.add(funcPo);
            }
        }
        mapper.insert(po);
        if (!list.isEmpty()) {
            for (RoleFuncPo funcPo : list) {
                funcMapper.insert(funcPo);
            }
        }

    }

    @Override
    public List<Map<String, Object>> allRoles() throws Exception {
        return mapper.list();
    }

    @Override
    public void edit(RolePo po, List<String> funs) throws Exception {
        List<String> db = funcMapper.getRoleFunc(po.getId());
        CompareUtils.compareString(db, funs, new CompareUtils.Compare<String>() {
            @Override
            public void onAdd(List<String> funs) throws Exception {
                for (String fun : funs) {
                    RoleFuncPo rolePo = new RoleFuncPo();
                    rolePo.setRoleId(po.getId());
                    rolePo.setFuncId(fun);
                    funcMapper.insert(rolePo);
                }
            }

            @Override
            public void onDel(List<String> funs) throws Exception {
                for (String fun : funs) {
                    RoleFuncPo rolePo = new RoleFuncPo();
                    rolePo.setRoleId(po.getId());
                    rolePo.setFuncId(fun);
                    funcMapper.delete(rolePo);
                }
            }
        });
        mapper.updateByPrimaryKeySelective(po);
    }

    @Override
    public Map<String, Object> get(String id) throws Exception {
        Map<String, Object> map = mapper.get(id);
        if (map == null)
            return map;
        String roles = (String) map.get("funs");
        if (roles == null) {
            map.put("funs", new String[]{});
        } else {
            map.put("funs", roles.split(","));
        }
        return map;
    }


}
