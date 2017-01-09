package com.shundian.frame.api.mis;

import com.shundian.lib.envm.EnumType;

/**
 * Created by TJ on 2017/1/10.
 */
public enum AreaLevel implements EnumType {
  
  ROOT("RT", "系统",0),
  PROVINCIAL("PR", "省",1),
  CIVIC("C", "市",2),
  DISTRICT_COUNTY("DC", "区/县",3),
  TOWN_STREET("TS", "镇/街",4);
  
  private AreaLevel(String key, String value,Integer level) {
    this.key = key;
    this.value = value;
    this.level = level;
  }
  
  private Integer level;
  
  private String key;
  
  private String value;
  
  @Override
  public String getKey() {
    return key;
  }
  
  @Override
  public String getValue() {
    return value;
  }
  
  public Integer getLevel() {
    return level;
  }
}
