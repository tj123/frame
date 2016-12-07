package com.shundian.frame.action.sys;

import com.shundian.frame.api.service.CityService;
import com.shundian.frame.api.service.sys.MenuService;
import com.shundian.lib.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/menu")
public class MenuAction {

    @Autowired
    private MenuService menuService;

    @Autowired
    private CityService cityService;

//    @RequestMapping
//    public Result<?> list(Page page, HttpServletResponse response){
//        Result<Object> result = new Result<Object>();
//
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        try {
//            result.ok(menuService.list(page));
//        } catch (Exception e) {
//            result.error(e.getMessage());
//        }
//
//        return result;
//    }

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
