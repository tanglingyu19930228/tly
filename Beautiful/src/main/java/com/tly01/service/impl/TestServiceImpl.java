package com.tly01.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tly01.dao.TestDao;
import com.tly01.service.TestService;

@Service("testService")
public class TestServiceImpl implements TestService{
    @Autowired
    private TestDao testDao;
	@Override
	public List<HashMap<String, Object>> sel() {
		return testDao.find();
	}
}
