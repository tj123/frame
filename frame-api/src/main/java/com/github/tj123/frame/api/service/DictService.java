package com.github.tj123.frame.api.service;

import java.util.List;
import java.util.Map;

/**
 * Created by TJ on 2017/11/17.
 */
public interface DictService {


    /**
     * @param type         "code","enum" 默认枚举
     * @param code         字典的编码 或者是 枚举值
     * @param departmentId 部门
     * @return 没有递归处理
     * @throws Exception
     */
    List<Map<String, Object>> getDict(String type, String code, String departmentId) throws Exception;

}
