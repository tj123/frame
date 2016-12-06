package com.shundian.frame.dao;

import com.github.tj123.db.Page;

import java.util.Map;

public interface MenuDao {

    Map<String, Object> list(Page page) throws Exception;
}
