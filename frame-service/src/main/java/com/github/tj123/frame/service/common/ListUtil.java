package com.github.tj123.frame.service.common;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by TJ on 2017/11/20.
 */
public class ListUtil {

    /**
     * 从 list map 中查找元素
     *
     * @param key
     * @param value
     * @param list
     * @return
     */
    public static Map<String, Object> findBykey(List<Map<String, Object>> list, String key, Object value) {
        if (list == null || list.isEmpty() || key == null || value == null) {
            return null;
        }
        for (Map<String, Object> map : list) {
            if (value.equals(map.get(key))) {
                return map;
            }
        }
        return null;
    }

    /**
     * 从list中移除取值为 object 的 map
     *
     * @param list
     * @param key
     * @param object
     */
    public static void removeMapValueEq(List<Map<String, Object>> list, String key, Object object) {
        if (list == null || list.isEmpty() || key == null || object == null) {
            return;
        }
        Iterator<Map<String, Object>> iterator = list.iterator();
        while (iterator.hasNext()) {
            Map<String, Object> map = iterator.next();
            if (object.equals(map.get(key))) {
                iterator.remove();
                return;
            }
        }

    }

}
