package com.github.tj123.frame.service.common;


import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Created by TJ on 2017/10/12.
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {

}
