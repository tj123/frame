package com.shundian.frame.test;

import com.shundian.lib.util.PasswordUtil;
import org.junit.Test;

import java.security.MessageDigest;

/**
 * Created by TJ on 2017/1/11.
 */
public class Test1 {
  
  @Test
  public void test1() throws Exception {
    MessageDigest md5 = MessageDigest.getInstance("md5");
    System.out.println(PasswordUtil.encrypt(new String(md5.digest("111111".getBytes("utf-8")),"utf-8")));
  }
  
  
}


