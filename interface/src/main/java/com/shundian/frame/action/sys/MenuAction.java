package com.shundian.frame.action.sys;

import com.github.tj123.db.Page;
import com.shundian.frame.api.sys.MenuService;
import com.shundian.frame.service.CityService;
import com.shundian.lib.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/sys/menu")
public class MenuAction {

    @Autowired
    private MenuService menuService;

    @Autowired
    private CityService cityService;

    @RequestMapping
    public Result<?> list(Page page, HttpServletResponse response){
        Result<Object> result = new Result<Object>();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            result.ok(menuService.list(page));
        } catch (Exception e) {
            result.error(e.getMessage());
        }

        return result;
    }

    @RequestMapping("/1")
    public Result<?> listCity(){
        Result<Object> result = new Result<Object>();
        try {
            result.ok(cityService.list());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }




}
