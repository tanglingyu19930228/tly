package com.tly.dubbo;

import com.tly.model.City;

public interface CityDubboService {
	/**
	 * 根据城市名称,查询城市信息
	 */
	City findCityByName(String cityName);

}
