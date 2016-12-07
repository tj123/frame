package com.shundian.frame.service.impl.sys;


import com.shundian.frame.api.service.sys.MenuService;
import com.shundian.frame.dao.MenuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl implements MenuService{

    @Autowired
    private MenuDao menuDao;

//    public Map<String, Object> list(Page page) throws Exception {
//        return menuDao.list(page.deft());
//    }


}
