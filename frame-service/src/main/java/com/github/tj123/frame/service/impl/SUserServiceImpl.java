package com.github.tj123.frame.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.tj123.frame.api.common.PageRequest;
import com.github.tj123.frame.api.common.PageResponse;
import com.github.tj123.frame.api.common.exception.MessageException;
import com.github.tj123.frame.api.common.utils.CompareUtils;
import com.github.tj123.frame.api.common.utils.PasswordUtils;
import com.github.tj123.frame.api.common.utils.UuidUtils;
import com.github.tj123.frame.api.pojo.po.SUserPo;
import com.github.tj123.frame.api.pojo.po.SUserRolePo;
import com.github.tj123.frame.api.service.SUserService;
import com.github.tj123.frame.service.common.PageUtils;
import com.github.tj123.frame.service.mapper.SDepMapper;
import com.github.tj123.frame.service.mapper.SUserMapper;
import com.github.tj123.frame.service.mapper.SUserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class SUserServiceImpl implements SUserService {

    @Autowired
    private SUserMapper mapper;

    @Autowired
    SDepMapper depMapper;

    @Autowired
    SUserRoleMapper userRoleMapper;

    @Override
    @Transactional
    public void add(SUserPo po, List<String> roles) throws Exception {
        if (po.getPassword() == null || po.getPassword().trim().equals(""))
            po.setPassword(PasswordUtils.encrypt("96e79218965eb72c92a549dd5a330112"));
        String id = UuidUtils.getUUID();
        po.setId(id);
        mapper.insert(po);
        for (String role : roles) {
            SUserRolePo userRolePo = new SUserRolePo();
            userRolePo.setUserId(id);
            userRolePo.setRoleId(role);
            userRoleMapper.insert(userRolePo);
        }

    }

    @Override
    public void del(String id) throws Exception {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional
    public void edit(SUserPo po, List<String> roles) throws Exception {
        List<String> db = userRoleMapper.getUserRole(po.getId());
        CompareUtils.compareString(db, roles, new CompareUtils.Compare<String>() {

            @Override
            public void onAdd(List<String> content) throws Exception {
                for (String role : content) {
                    SUserRolePo rolePo = new SUserRolePo();
                    rolePo.setUserId(po.getId());
                    rolePo.setRoleId(role);
                    userRoleMapper.insert(rolePo);
                }
            }

            @Override
            public void onDel(List<String> content) throws Exception {
                for (String role : content) {
                    SUserRolePo rolePo = new SUserRolePo();
                    rolePo.setUserId(po.getId());
                    rolePo.setRoleId(role);
                    userRoleMapper.delete(rolePo);
                }
            }
        });
        mapper.updateByPrimaryKeySelective(po);
    }

    @Override
    public Map<String, Object> get2(String id) throws Exception {
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
        return PageUtils.query(request, () -> mapper.list(request));
    }


    @Override
    public String login(String userName, String password) throws Exception {
        if (userName == null || userName.trim().equals(""))
            throw new MessageException("请输入用户名");
        if (password == null || password.trim().equals(""))
            throw new MessageException("请输入密码");
        Map<String, Object> map = mapper.selectUser(userName);
        if (map == null)
            throw new MessageException("用户名或密码错误");
        String userId = (String) map.get("id");
        String dbPassword = (String) map.get("password");
        if (dbPassword == null || dbPassword.trim().equals(""))
            throw new MessageException("用户名或密码错误");
        if (!PasswordUtils.isMatch(password, dbPassword))
            throw new MessageException("用户名或密码错误");
        return userId;
    }

    @Override
    public List<Map<String, Object>> auth(String userId) throws Exception {
        return mapper.auth(userId);
    }

    @Override
    public Boolean isExist(String username) throws Exception {
        if (username == null)
            return true;
        return mapper.isExistUsername(username) > 0;
    }

    @Override
    public Map<String, Object> session(String userId) throws Exception {
        return mapper.session(userId);
    }


}
