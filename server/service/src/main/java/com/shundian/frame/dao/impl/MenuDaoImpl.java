package com.shundian.frame.dao.impl;

import com.github.tj123.db.MysqlTemplate;
import com.github.tj123.db.Page;
import com.github.tj123.db.PageResult;
import com.shundian.frame.dao.MenuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MenuDaoImpl implements MenuDao {

    @Autowired
    private MysqlTemplate mysqlTemplate;

    public Map<String, Object> list(Page page) throws Exception {
        return mysqlTemplate.findPage("select * from tsys_menu",page);
    }
}
