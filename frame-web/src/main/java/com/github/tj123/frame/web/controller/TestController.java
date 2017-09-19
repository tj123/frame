package com.github.tj123.frame.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by TJ on 2017/9/19.
 */
@RestController
public class TestController {

    @RequestMapping
    public String main(){
        return "asdfasdfasdf";
    }

}
