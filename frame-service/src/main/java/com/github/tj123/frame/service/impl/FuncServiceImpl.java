package com.github.tj123.frame.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.tj123.common.auth.compare.AuthorizationCompare;
import com.github.tj123.common.auth.compare.Compare;
import com.github.tj123.common.auth.compare.CompareFunction;
import com.github.tj123.frame.api.common.PageRequest;
import com.github.tj123.frame.api.common.PageResponse;
import com.github.tj123.frame.api.po.sys.FunctionPo;
import com.github.tj123.frame.api.service.FuncService;
import com.github.tj123.frame.service.common.PageUtils;
import com.github.tj123.frame.service.mapper.FuncMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Service
public class FuncServiceImpl implements FuncService {

    @Autowired
    FuncMapper mapper;

    @Override
    public PageResponse<Map<String, Object>> list(PageRequest request) throws Exception {
        return PageUtils.query(request, () -> mapper.list());
    }


    @Override
    public void scan(List<Map<String, Object>> scanList) throws Exception {
        List<Map<String, Object>> dbList = mapper.list();
        AuthorizationCompare.compare(dbList, scanList, new Compare() {
            @Override
            public void onAddFunction(List<CompareFunction> list) throws Exception {
                for (CompareFunction func : list) {
                    FunctionPo po = new FunctionPo();
                    po.setId(func.getId());
                    po.setUid(func.getUid());
                    po.setParentUid(func.getParentUid());
                    po.setKey(func.getKey());
                    po.setName(func.getName());
                    po.setFullName(func.getFullName());
                    mapper.insert(po);
                }
            }

            @Override
            public void onModifyFunction(List<CompareFunction> list) throws Exception {
                for (CompareFunction func : list) {
                    FunctionPo po = new FunctionPo();
                    po.setId(func.getId());
                    po.setUid(func.getUid());
                    po.setParentUid(func.getParentUid());
                    po.setKey(func.getKey());
                    po.setName(func.getName());
                    po.setFullName(func.getFullName());
                    mapper.updateByPrimaryKey(po);
                }
            }

            @Override
            public void onDeleteFunction(List<CompareFunction> list) throws Exception {
                for (CompareFunction func : list) {
                    mapper.deleteByPrimaryKey(func.getId());
                }
            }
        });
    }

    @Override
    public List<Map<String, Object>> all() throws Exception {
        return mapper.listAll();
    }


}
