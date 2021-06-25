package com.radosav.master.rad.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.radosav.master.rad.controller.BookController;
import com.radosav.master.rad.dto.BookDB;

@RestController
@RequestMapping("/book")
public class BookRest {
	
	@Autowired
	private BookController controller;
	
	@GetMapping(value = "/get", produces = "application/json")
	public List<BookDB> getBooks(){
		List<BookDB> result = controller.getBooks();
		System.err.println("result = "+result);
		return result;
	}
	
	@PostMapping(value = "/save", consumes = "application/json", produces = "application/json")
	public BookDB saveBook(@RequestBody BookDB bookDB) {
		System.err.println("bookDB = "+bookDB);
		return controller.saveBook(bookDB);
	}
	
	@PostMapping(value = "/update", consumes = "application/json", produces = "application/json")
	public BookDB updateBook(@RequestBody BookDB bookDB) {
		System.err.println("bookDB = "+bookDB);
		return controller.updateBook(bookDB);
	}
	
	@GetMapping(value="/remove")
	public void removeBook(@RequestParam Integer id) {
		System.err.println("id = "+id);
		controller.removeBook(id);
	}
}
