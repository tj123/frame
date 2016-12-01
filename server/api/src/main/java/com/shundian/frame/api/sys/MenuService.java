package com.shundian.frame.api.sys;

import com.github.tj123.db.Page;

import java.util.Map;

public interface MenuService {

    Map<String,Object> list(Page page) throws Exception;

}
