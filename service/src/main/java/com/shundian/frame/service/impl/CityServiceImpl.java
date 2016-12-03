package com.shundian.frame.service.impl;


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
		return cityMapper.selectAll();
	}


}
