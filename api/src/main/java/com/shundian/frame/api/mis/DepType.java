package com.shundian.frame.api.mis;

import com.shundian.lib.envm.EnumType;

/**
 * Created by TJ on 2017/1/10.
 */
public enum DepType implements EnumType {
  
  /**
   *Food and Drug Administration
   */
  FDA("FDA", "食药局"),
  
  /**
   * Education Bureau
   */
  EB("EB", "教育局"),
  
  /**
   * public security bureau
   */
  PSB("PSB", "公安厅/公安局/派出所");
  
  private String key;
  private String value;
  
  private DepType(String key, String value) {
    this.key = key;
    this.value = value;
  }
  
  @Override
  public String getKey() {
    return null;
  }
  
  @Override
  public String getValue() {
    return null;
  }
}
