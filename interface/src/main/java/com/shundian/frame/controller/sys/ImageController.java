package com.shundian.frame.controller.sys;

import com.shundian.lib.authorize.Authorize;
import com.shundian.lib.authorize.AuthorizeType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 图片后台预览工具类
 */
@Slf4j
@RestController
@RequestMapping("/sys/img")
@Authorize(AuthorizeType.ALL)
public class ImageController {


    @RequestMapping("/to64")
    public void toBase64(MultipartFile multipartFiles){

        com.baidu.ueditor.ActionEnter actionEnter = null;

    }









}
