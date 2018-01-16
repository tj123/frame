package com.github.tj123.frame.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.tj123.frame.api.common.PageRequest;
import com.github.tj123.frame.api.common.PageResponse;
import com.github.tj123.frame.api.po.sys.FunctionPo;
import com.github.tj123.frame.api.service.FuncService;
import com.github.tj123.frame.service.common.ListUtil;
import com.github.tj123.frame.service.common.PageUtils;
import com.github.tj123.frame.service.common.UuidUtil;
import com.github.tj123.frame.service.mapper.FuncMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
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
        List<FunctionPo> addList = new ArrayList<>();
        List<FunctionPo> modifyList = new ArrayList<>();
        for (Map<String, Object> scan : scanList) {
            Map<String, Object> db = ListUtil.findBykey(dbList, "uid", scan.get("id"));
            if (db == null) {
                FunctionPo po = new FunctionPo();
                po.setId(UuidUtil.getUUID());
                po.setUid((String) scan.get("id"));
                po.setParentUid((String) scan.get("parentId"));
                po.setKey((String) scan.get("key"));
                po.setName((String) scan.get("name"));
                po.setFullName((String) scan.get("fullName"));
                addList.add(po);
                ListUtil.removeMapValueEq(dbList, "uid", scan.get("id"));
                continue;
            }
            FunctionPo modifyPo = new FunctionPo();
            boolean add = false;
            Object parentId = scan.get("parentId");
            if (parentId != null && !parentId.equals(db.get("parentId"))) {
                add = true;
                modifyPo.setId((String) db.get("id"));
                modifyPo.setParentUid((String) scan.get("parentId"));
            }
            if (!scan.get("key").equals(db.get("key"))) {
                add = true;
                modifyPo.setId((String) db.get("id"));
                modifyPo.setKey((String) scan.get("key"));
            }
            if (!scan.get("name").equals(db.get("name"))) {
                add = true;
                modifyPo.setId((String) db.get("id"));
                modifyPo.setName((String) scan.get("name"));
            }
            if (!scan.get("fullName").equals(db.get("fullName"))) {
                add = true;
                modifyPo.setId((String) db.get("id"));
                modifyPo.setFullName((String) scan.get("fullName"));
            }
            if (add) {
                modifyList.add(modifyPo);
            }
            ListUtil.removeMapValueEq(dbList, "uid", scan.get("id"));
        }
        if (!addList.isEmpty()) {

            for (FunctionPo functionPo : addList) {
                mapper.insert(functionPo);
            }
            //mapper.insertList(addList);
        }
        for (FunctionPo po : modifyList) {
            mapper.updateByPrimaryKeySelective(po);
        }
        if (!dbList.isEmpty()) {
            for (Map<String, Object> map : dbList) {
                mapper.deleteByPrimaryKey(map.get("id"));
            }

        }

    }

    @Override
    public List<Map<String, Object>> all() throws Exception {
        return mapper.listAll();
    }


}
