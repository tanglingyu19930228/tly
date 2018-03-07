package com.tly01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tly01.dto.Book;
import com.tly01.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {
	@Autowired
	private BookService bookService;
	@RequestMapping("/sel")
	public Book getById(@RequestParam Integer id) {
		return bookService.getAll(id);
	}

}
