package com.tly.dubbo;

import com.tly.model.City;

public interface CityDubboService {
        
	City findCityByName(String cityName);
}
