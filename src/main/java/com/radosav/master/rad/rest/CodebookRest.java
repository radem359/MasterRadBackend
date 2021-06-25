package com.radosav.master.rad.rest;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.radosav.master.rad.controller.CodebookController;

@RestController
@RequestMapping("/codebook")
public class CodebookRest {
	

	@Autowired
	private CodebookController codebookCont;

	@SuppressWarnings("rawtypes")
	@GetMapping(value = "/get")
	public HashMap<String, List> getCodebooks(@RequestParam List<String> codebook) {
		
		HashMap<String, List> result = new HashMap<>();
		if (codebook.contains("books")) {
			result.put("books", codebookCont.getBooks());
		}
		if (codebook.contains("authors")) {
			result.put("authors", codebookCont.getAuthors());
		}
		if (codebook.contains("genres")) {
			result.put("genres", codebookCont.getGenres());
		}
		if (codebook.contains("languages")) {
			result.put("languages", codebookCont.getLanguages());
		}
		if (codebook.contains("nationalities")) {
			result.put("nationalities", codebookCont.getNationalities());
		}
		if (codebook.contains("users")) {
			result.put("users", codebookCont.getUsers());
		}
		
		return result;
	}
	
}
