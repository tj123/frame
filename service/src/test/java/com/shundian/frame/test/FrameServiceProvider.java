/*
 * Copyright 1999-2011 Alibaba Group.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.shundian.frame.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class FrameServiceProvider {
  
  public static void main(String[] args) throws IOException {
    ClassPathXmlApplicationContext context =
        new ClassPathXmlApplicationContext(new String[]{"classpath:spring/spring-context.xml"});
    context.start();
    System.in.read(); // 按任意键退出
  }
  
}