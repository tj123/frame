package com.github.tj123.frame.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.tj123.frame.api.common.PageRequest;
import com.github.tj123.frame.api.common.PageResponse;
import com.github.tj123.frame.api.common.utils.UuidUtils;
import com.github.tj123.frame.api.pojo.po.SDictItemPo;
import com.github.tj123.frame.api.pojo.po.SDictPo;
import com.github.tj123.frame.api.service.SDictService;
import com.github.tj123.frame.service.common.PageUtils;
import com.github.tj123.frame.service.mapper.SDictItemMapper;
import com.github.tj123.frame.service.mapper.SDictMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Service
public class SDictServiceImpl implements SDictService {

    @Autowired
    private SDictMapper mapper;

    @Autowired
    SDictItemMapper itemMapper;

    @Override
    @Transactional
    public void add(SDictPo po, List<SDictItemPo> items) throws Exception {
        po.setOrder_(new Random().nextInt(20));
        String id = UuidUtils.getUUID();
        po.setId(id);
        mapper.insert(po);
        for (SDictItemPo item : items) {
            item.setDictId(id);
            itemMapper.insert(item);
        }
    }

    @Override
    public void del(String id) throws Exception {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional
    public void edit(SDictPo po, List<SDictItemPo> items) throws Exception {
        mapper.updateByPrimaryKeySelective(po);
        String dictId = po.getId();
        SDictItemPo itemPo = new SDictItemPo();
        itemPo.setDictId(dictId);
        List<SDictItemPo> db = itemMapper.select(itemPo);
        if (db == null || db.isEmpty()) {
            for (SDictItemPo item : items) {
                item.setDictId(dictId);
                itemMapper.insert(item);
            }
            return;
        }
        if (items.isEmpty()) {
            itemMapper.delete(itemPo);
            return;
        }
        List<SDictItemPo> addList = new ArrayList<>();
        List<SDictItemPo> modifyList = new ArrayList<>();
        for (SDictItemPo item : items) {
            if (item.getId() == null) {
                item.setDictId(dictId);
                addList.add(item);
            } else {
                SDictItemPo dbPo = findById(db, item.getId());
                if (dbPo != null) {
                    if (!(item.getKey().equals(dbPo.getKey())
                            && item.getValue().equals(dbPo.getValue())
                            && item.getGroup().equals(dbPo.getGroup()))) {
                        item.setId(dbPo.getId());
                        item.setDictId(dictId);
                        modifyList.add(item);
                    }
                    removeById(db, item.getId());
                }
            }
        }
        for (SDictItemPo dictItemPo : addList) {
            itemMapper.insert(dictItemPo);
        }
        for (SDictItemPo dictItemPo : modifyList) {
            itemMapper.updateByPrimaryKey(dictItemPo);
        }
        for (SDictItemPo dictItemPo : db) {
            itemMapper.deleteByPrimaryKey(dictItemPo.getId());
        }

    }

    private static void removeById(List<SDictItemPo> items, String id) {
        if (items == null || items.isEmpty() || id == null) {
            return;
        }
        Iterator<SDictItemPo> iterator = items.iterator();
        while (iterator.hasNext()) {
            SDictItemPo next = iterator.next();
            if (id.equals(next.getId())) {
                iterator.remove();
                return;
            }
        }
    }

    private static SDictItemPo findById(List<SDictItemPo> items, String id) {
        if (items == null || items.isEmpty() || id == null) {
            return null;
        }
        for (SDictItemPo item : items) {
            if (id.equals(item.getId())) {
                return item;
            }
        }
        return null;
    }

    @Override
    public Map<String, Object> get2(String id) throws Exception {
        Map<String, Object> map = mapper.get(id);
        SDictItemPo po = new SDictItemPo();
        po.setDictId(id);
        if (map != null) {
            map.put("items", itemMapper.select(po));
        }
        return map;
    }

    @Override
    public PageResponse<Map<String, Object>> list(PageRequest request) throws Exception {
        return PageUtils.query(request, () -> mapper.list(request));
    }

    private String BASE_ENUM_PATH = "com.github.tj123.frame.api.envm.";


    @Override
    public List<Map<String, Object>> getDict(String type, String code, String depId) throws Exception {
        List<Map<String, Object>> list = new ArrayList<>();
        if (code == null || code.trim().equals(""))
            return list;
        if (type == null) {
            type = "enum";
        }
        type = type.trim();
        if (!type.equals("enum") && !type.equals("dtbs")) {
            type = "enum";
        }
        if (type.equals("enum")) {
            try {
                Class<?> clazz = Class.forName(BASE_ENUM_PATH + code);
                Object[] constants = clazz.getEnumConstants();
                for (Object constant : constants) {
                    if (constant == null)
                        continue;
                    Map<String, Object> map = new HashMap<>();
                    Object key = clazz.getMethod("getKey").invoke(constant);
                    map.put("key", String.valueOf(key == null ? constant : key));
                    Object value = clazz.getMethod("getValue").invoke(constant);
                    map.put("val", value == null ? "" : String.valueOf(value));
                    list.add(map);
                }
            } catch (Exception e) {
                log.error("获取枚举失败", e);
            }
        } else {

        }
        return list;
    }

}
