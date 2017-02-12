package com.shundian.frame.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RunTask {
  
  @Scheduled(fixedRate = Long.MAX_VALUE)
  public void runTask(){
    
  }
  
  
}
