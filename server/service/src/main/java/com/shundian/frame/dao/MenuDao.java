package com.shundian.frame.dao;

import com.github.tj123.db.MysqlTemplate;
import com.github.tj123.db.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public interface MenuDao {

    public Map<String, Object> list(Page page) throws Exception;
}
