package com.shundian.frame.api.envm;

import com.shundian.lib.envm.EnumType;

import java.io.File;

/**
 * 上传路径枚举
 */
public enum UploadDirectory implements EnumType{

    SYS("sys","系统");

    private final String value;
    private final String key;

//    /**
//     * linux
//     */
//    public static final String BASE_DIRECTORY = "/root/files";

    /**
     * windows
     */
    public static final String BASE_DIRECTORY = "D:\\files";

    UploadDirectory(String key, String value){
        this.key = key;
        this.value = value;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getValue() {
        return value;
    }

    /**
     * 子路径
     * @return
     */
    public String getSubDirectory(){
        return key;
    }

    /**
     * 文件绝对路径
     * @return
     */
    public String getDirectory(){
        return BASE_DIRECTORY + File.separator + key;
    }
}
