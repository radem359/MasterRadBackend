package com.radosav.master.rad.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.radosav.master.rad.controller.AuthorController;
import com.radosav.master.rad.dto.AuthorDB;

@RestController
@RequestMapping("/author")
public class AuthorRest {
	
	@Autowired
	private AuthorController controller;
	
	@GetMapping(value = "/get", produces = "application/json")
	public List<AuthorDB> getAuthors(){
		List<AuthorDB> result = controller.getAuthors();
		System.err.println("result = "+result);
		return result;
	}
	
	@PostMapping(value = "/save", consumes = "application/json", produces = "application/json")
	public AuthorDB saveAuthor(@RequestBody AuthorDB authorDB) {
		System.err.println("authorDB = "+authorDB.toString());
		return controller.saveAuthor(authorDB);
	}
	
	@PostMapping(value = "/update", consumes = "application/json", produces = "application/json")
	public AuthorDB updateAuthor(@RequestBody AuthorDB authorDB) {
		System.err.println("authorDB = "+authorDB.toString());
		return controller.updateAuthor(authorDB);
	}
	
	@GetMapping(value = "/remove")
	public void removeAuthor(@RequestParam Integer id) {
		controller.removeAuthor(id);
	}
}
