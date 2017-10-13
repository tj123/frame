package com.github.tj123.frame.api.common;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by TJ on 2017/10/13.
 */
@Slf4j
public class PageRequest extends HashMap<String, Object>{

    public static final int DEFAULT_SIZE = 10;
    public static final int MAX_SIZE = 100;

    /**
     * 只能通过静态方法来构造
     *
     * @param map
     * @return
     */
    public static PageRequest create(Map<String, Object> map) {
        PageRequest pageRequest = new PageRequest();
        if (map == null) {
            return pageRequest;
        }
        for (Entry<String, Object> entry : map.entrySet()) {
            pageRequest.put(entry.getKey(), entry.getValue());
        }
        pageRequest.defaultValue();
        return pageRequest;
    }

    /**
     * 设置默认值
     */
    public void defaultValue() {
        put("page", getPage());
        put("size", getSize());
    }

    /**
     * 第几页
     *
     * @return
     */
    public int getPage() {
        Integer page = getInteger("page");
        if (page == null) {
            return 1;
        }
        return page;
    }

    /**
     * 每页长度
     *
     * @return
     */
    public int getSize() {
        Integer size = getInteger("size");
        if (size == null) {
            return DEFAULT_SIZE;
        }
        if (size > MAX_SIZE) {
            return MAX_SIZE;
        }
        return size;
    }

    /**
     * 获取数字
     *
     * @param key
     * @return
     */
    public Integer getInteger(String key) {
        Object val = get(key);
        if (val == null) {
            return null;
        }
        try {
            Integer integer = Integer.valueOf(String.valueOf(val));
            return integer;
        } catch (NumberFormatException e) {
            if (log.isDebugEnabled()) {
                log.debug("", e);
            }
        }
        return null;
    }

    /**
     * 获取字符
     *
     * @param key
     * @return
     */
    public String getString(String key) {
        Object val = get(key);
        if (val == null) {
            return "";
        }
        return String.valueOf(val);
    }

    /**
     * 获取 boolean 值类型
     *
     * @param key
     * @return
     */
    public boolean is(String key) {
        return "true".equals(getString(key).trim());
    }

}
