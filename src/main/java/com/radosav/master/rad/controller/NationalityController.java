package com.radosav.master.rad.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.radosav.master.rad.dto.AuthorDB;
import com.radosav.master.rad.dto.NationalityDB;
import com.radosav.master.rad.model.Author;
import com.radosav.master.rad.model.Nationality;
import com.radosav.master.rad.repository.AuthorRepository;
import com.radosav.master.rad.repository.NationalityRepository;

@Controller
@Transactional
public class NationalityController {
	
	@Autowired
	private NationalityRepository nationalityRepository;

	@Autowired
	private AuthorRepository authorRepository;
	
	public List<NationalityDB> getNationalities(){
		List<NationalityDB> result = new LinkedList<NationalityDB>();
		
		List<Nationality> nationalities = nationalityRepository.findAll();
		for(Nationality n : nationalities) {
			NationalityDB nationalityDB = new NationalityDB(n, true);
			result.add(nationalityDB);
		}
		
		return result;
	}
	
	public void removeNationality(Integer id) {
		Nationality nationality = nationalityRepository.getOne(id);
		nationalityRepository.delete(nationality);
	}
	
	public NationalityDB saveNationality(NationalityDB nationalityDB) {
		Nationality nationality = new Nationality();
		setNationality(nationality, nationalityDB);
		
		nationalityRepository.save(nationality);
		nationalityDB.setId(nationality.getId());
		
		return nationalityDB;
	}
	
	public NationalityDB updateNationality(NationalityDB nationalityDB) {
		Nationality nationality = nationalityRepository.getOne(nationalityDB.getId());
		setNationality(nationality, nationalityDB);
		
		nationalityRepository.save(nationality);
		
		return nationalityDB;
	}
	
	private void setNationality(Nationality nationality, NationalityDB nationalityDB) {
		nationality.setNationalityName(nationalityDB.getNationalityName());
		
		if(nationalityDB.getAuthors() != null) {
			List<Author> authors = new LinkedList<Author>();
			for(AuthorDB a : nationalityDB.getAuthors()) {
				Author author = authorRepository.getOne(a.getId());
				authors.add(author);
			}
			nationality.setAuthors(authors);
		}
	}
	
}
