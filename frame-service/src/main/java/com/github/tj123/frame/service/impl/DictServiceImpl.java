package com.github.tj123.frame.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.tj123.frame.api.service.DictService;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by TJ on 2017/9/19.
 */
@Slf4j
@Service
public class DictServiceImpl implements DictService {


    private String BASE_ENUM_PATH = "com.github.tj123.frame.api.envm.";


    @Override
    public List<Map<String, Object>> getDict(String type, String code, String departmentId) throws Exception {
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
