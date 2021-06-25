package com.radosav.master.rad.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.radosav.master.rad.controller.LanguageController;
import com.radosav.master.rad.dto.LanguageDB;

@RestController
@RequestMapping("/language")
public class LanguageRest {

	@Autowired
	private LanguageController controller;
	
	@GetMapping(value = "/get", produces = "application/json")
	public List<LanguageDB> getAuthors(){
		List<LanguageDB> result = controller.getLanguages();
		return result;
	}
	
	@PostMapping(value = "/save", consumes = "application/json", produces = "application/json")
	public LanguageDB saveLanguage(@RequestBody LanguageDB languageDB){
		return controller.saveLangugage(languageDB);	
	}
	
	@PostMapping(value = "/update", consumes = "application/json", produces = "application/json")
	public LanguageDB updateLanguage(@RequestBody LanguageDB languageDB){
		return controller.updateLangugage(languageDB);	
	}
	
	@GetMapping(value = "/remove")
	public void removeLanguage(@RequestParam Integer id) {
		controller.removeLanguage(id);
	}
}
