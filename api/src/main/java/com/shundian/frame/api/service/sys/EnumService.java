package com.shundian.frame.api.service.sys;

import com.shundian.lib.envm.EnumType;

import java.util.List;
import java.util.Map;

/**
 * Created by TJ on 2017/1/10.
 */
public interface EnumService {


    /**
     * 把枚举转换为 [{key,value,_value}] 形式
     * @param enumClass
     * @param <E>
     * @return
     */
    <E extends Enum<E> & EnumType> List<Map<String, Object>> convertEnum(Class<E> enumClass) throws Exception;

}
