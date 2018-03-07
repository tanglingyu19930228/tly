package com.tly01.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tly01.dao.BookMapper;
import com.tly01.dto.Book;
import com.tly01.service.BookService;

@Service("bookServiceImpl")
public class BookServiceImpl implements BookService{
	@Autowired
	private BookMapper bookMapper;
	@Override
	public Book getAll(Integer id) {
		return bookMapper.selectByPrimaryKey(id);
	}

}
