package com.shundian.frame.service.impl.sys;


import com.alibaba.dubbo.config.annotation.Service;
import com.shundian.frame.api.service.sys.DepartmentService;
import com.shundian.frame.common.PageUtil;
import com.shundian.frame.mapper.sys.DepartmentMapper;
import com.shundian.lib.Page;
import com.shundian.lib.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Slf4j
@Component
@Service(version = "1.0.0")
public class DepartmentServiceImpl implements DepartmentService {
  
  
  @Autowired
  private DepartmentMapper mapper;
  
  
  @Override
  public PageResult<Map<String, Object>> list(Page page) throws Exception {
    PageUtil<Map<String, Object>> pageUtil = new PageUtil<>();
    pageUtil.startPage(page);
    return pageUtil.assembleResult(mapper.list(page.assembleSearch()));
  }
  
  @Override
  public List<Map<String, Object>> search(String name, int lmt) throws Exception {
    return mapper.search(name, lmt);
  }
}
