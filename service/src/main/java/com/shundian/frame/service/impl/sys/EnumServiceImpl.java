package com.shundian.frame.service.impl.sys;


import com.alibaba.dubbo.config.annotation.Service;
import com.shundian.frame.api.service.sys.EnumService;
import com.shundian.lib.envm.EnumType;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Service(version = "1.0.0")
public class EnumServiceImpl implements EnumService {

  @Override
  public <E extends Enum<E> & EnumType> List<Map<String, Object>> convertEnum(Class<E> enumClass) throws Exception{
    List<Map<String,Object>> list = new ArrayList<>();
    E[] enumConstants = enumClass.getEnumConstants();
    for (E enumConstant : enumConstants) {
      Map<String,Object> map = new HashMap<>();
      map.put("key",enumConstant.getKey());
      map.put("value",enumConstant.getValue());
      map.put("_value",String.valueOf(enumConstant));
      list.add(map);
    }
    return list;
  }

}
