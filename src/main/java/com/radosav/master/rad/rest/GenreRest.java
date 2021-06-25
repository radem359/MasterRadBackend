package com.radosav.master.rad.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.radosav.master.rad.controller.GenreController;
import com.radosav.master.rad.dto.GenreDB;

@RestController
@RequestMapping("/genre")
public class GenreRest {
	
	@Autowired
	private GenreController controller;
	
	@GetMapping(value = "/get", produces = "application/json")
	public List<GenreDB> getGenres(){
		List<GenreDB> result = controller.getGenres();
		return result;
	}
	

	@PostMapping(value = "/save", consumes = "application/json", produces = "application/json")
	public GenreDB saveGenre(@RequestBody GenreDB genreDB) {
		return controller.saveGenre(genreDB);
	}
	
	@PostMapping(value = "/update", consumes = "application/json", produces = "application/json")
	public GenreDB updateGenre(@RequestBody GenreDB genreDB) {
		return controller.updateGenre(genreDB);
	}
	
	@GetMapping(value = "/remove")
	public void removeGenre(@RequestParam Integer id) {
		controller.removeGenre(id);
	}
}
