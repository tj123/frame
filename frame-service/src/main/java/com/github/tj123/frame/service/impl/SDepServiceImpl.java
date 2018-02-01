package com.github.tj123.frame.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.tj123.frame.api.common.PageRequest;
import com.github.tj123.frame.api.common.PageResponse;
import com.github.tj123.frame.api.common.utils.AreaUtils;
import com.github.tj123.frame.api.common.utils.CompareUtils;
import com.github.tj123.frame.api.common.utils.UuidUtils;
import com.github.tj123.frame.api.pojo.po.SDepPo;
import com.github.tj123.frame.api.pojo.po.SDepRolePo;
import com.github.tj123.frame.api.service.SDepService;
import com.github.tj123.frame.service.common.PageUtils;
import com.github.tj123.frame.service.mapper.SAreaMapper;
import com.github.tj123.frame.service.mapper.SDepMapper;
import com.github.tj123.frame.service.mapper.SDepRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class SDepServiceImpl implements SDepService {

    @Autowired
    private SDepMapper mapper;

    @Autowired
    SAreaMapper areaMapper;

    @Autowired
    SDepRoleMapper depRoleMapper;

    @Override
    @Transactional
    public void add(SDepPo po, List<String> roles) throws Exception {
        String id = UuidUtils.getUUID();
        po.setId(id);
        mapper.insert(po);
        for (String role : roles) {
            SDepRolePo rs = new SDepRolePo();
            rs.setDepId(id);
            rs.setRoleId(role);
            depRoleMapper.insert(rs);
        }
    }

    @Override
    public void del(String id) throws Exception {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional
    public void edit(SDepPo po, List<String> roles) throws Exception {
        List<String> db = depRoleMapper.getDep(po.getId());
        CompareUtils.compareString(db, roles, new CompareUtils.Compare<String>() {
            @Override
            public void onAdd(List<String> content) throws Exception {
                for (String role : content) {
                    SDepRolePo rolePo = new SDepRolePo();
                    rolePo.setDepId(po.getId());
                    rolePo.setRoleId(role);
                    depRoleMapper.insert(rolePo);
                }
            }

            @Override
            public void onDel(List<String> content) throws Exception {
                for (String role : content) {
                    SDepRolePo rolePo = new SDepRolePo();
                    rolePo.setDepId(po.getId());
                    rolePo.setRoleId(role);
                    depRoleMapper.delete(rolePo);
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
        String roles = (String) map.get("roles");
        if (roles == null) {
            map.put("roles", new String[]{});
        } else {
            map.put("roles", roles.split(","));
        }
        return map;
    }

    @Override
    public PageResponse<Map<String, Object>> list(PageRequest request) throws Exception {
        request.put("areaId", AreaUtils.simple((String) request.get("areaId")));
        return PageUtils.query(request, () -> mapper.list(request));
    }

    @Override
    public List<Map<String, Object>> areas(String areaId) throws Exception {
        return areaMapper.areas(AreaUtils.simple(areaId));
    }

    @Override
    public List<Map<String, Object>> search(String name) throws Exception {
        return mapper.search(name);
    }

}
