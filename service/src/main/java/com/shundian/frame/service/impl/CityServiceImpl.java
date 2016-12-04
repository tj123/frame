package com.shundian.frame.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.shundian.frame.mapper.CityMapper;
import com.shundian.frame.model.City;
import com.shundian.frame.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl  implements CityService{

	@Autowired
	private CityMapper cityMapper;

	@Override
	public List<City> list() throws Exception {
//		City record = new City();
//		record.setName("我的名字");
//		record.setState(String.valueOf(Math.random()).replaceAll("\\.",""));
//		cityMapper.insert(record);

        Page<City> page = new Page<City>();

        page.setEndRow(10);

        PageHelper.startPage(page);
        //Page<Object> page = PageHelper.startPage(2, 2, true);
        List<City> cities = cityMapper.selectAll();

        System.out.println(page);

        return cities;
	}


}
