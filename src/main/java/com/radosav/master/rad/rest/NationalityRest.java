package com.radosav.master.rad.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.radosav.master.rad.controller.NationalityController;
import com.radosav.master.rad.dto.NationalityDB;

@RestController
@RequestMapping("/nationality")
public class NationalityRest {

	@Autowired
	private NationalityController controller;
	
	@GetMapping(value = "/get", produces = "application/json")
	public List<NationalityDB> getNationalities(){
		List<NationalityDB> result = controller.getNationalities();
		return result;
	}
	
	@PostMapping(value = "/save", consumes = "application/json", produces = "application/json")
	public NationalityDB saveNationality(@RequestBody NationalityDB nationalityDB) {
		return controller.saveNationality(nationalityDB);
	}
	
	@PostMapping(value = "/update", consumes = "application/json", produces = "application/json")
	public NationalityDB updateNationality(@RequestBody NationalityDB nationalityDB) {
		return controller.updateNationality(nationalityDB);
	}

	@GetMapping(value = "/remove")
	public void removeNationality(@RequestParam Integer id) {
		controller.removeNationality(id);
	}
}
