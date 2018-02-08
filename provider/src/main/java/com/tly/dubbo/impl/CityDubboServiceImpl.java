package com.tly.dubbo.impl;



import com.alibaba.dubbo.config.annotation.Service;
import com.tly.dubbo.CityDubboService;
import com.tly.model.City;

@Service(version="1.0.0")
public class CityDubboServiceImpl implements CityDubboService {

	@Override
	public City findCityByName(String cityName) {
		return new City(1L,2L,"温岭","是我的故乡");
	}

}
