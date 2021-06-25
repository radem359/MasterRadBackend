package com.radosav.master.rad.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.radosav.master.rad.dto.BookDB;
import com.radosav.master.rad.dto.LanguageDB;
import com.radosav.master.rad.model.Book;
import com.radosav.master.rad.model.Language;
import com.radosav.master.rad.repository.BookRepository;
import com.radosav.master.rad.repository.LanguageRepository;
import org.springframework.transaction.annotation.Transactional;

@Controller
@Transactional
public class LanguageController {
	
	@Autowired
	private LanguageRepository languageRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	public List<LanguageDB> getLanguages(){
		List<LanguageDB> result = new LinkedList<LanguageDB>();
		
		List<Language> languages = languageRepository.findAll();
		for(Language l: languages) {
			LanguageDB language = new LanguageDB(l, true);
			result.add(language);
		}
		
		return result;
	}
	
	public void removeLanguage(Integer id) {
		Language l = languageRepository.getOne(id);
		languageRepository.delete(l);
	}
	
	public LanguageDB saveLangugage(LanguageDB languageDB) {
		Language language = new Language();
		setLanguage(language, languageDB);
		
		languageRepository.save(language);
		languageDB.setId(language.getId());
		
		if(language.getBooks() != null) {
			for(Book b: language.getBooks()) {
				b.setLanguage(language);
				bookRepository.save(b);
			}
		}
		
		return languageDB;
	}
	
	public LanguageDB updateLangugage(LanguageDB languageDB) {
		Language language = languageRepository.getOne(languageDB.getId());
		setLanguage(language, languageDB);
		
		languageRepository.save(language);
		
		if(language.getBooks() != null) {
			List<Book> books = bookRepository.findAll();
			for(Book b: books) {
				if(!b.getLanguage().equals(language)) {
					if(language.getBooks().contains(b)) {
						b.setLanguage(language);
					}
				}
				
				if(b.getLanguage().equals(language)) {
					if(!language.getBooks().contains(b)) {
						b.setLanguage(null);
					}
				}
				
				bookRepository.save(b);
			}
		}
		
		return languageDB;
	}
	
	private void setLanguage(Language language, LanguageDB languageDB) {
		language.setLanguageName(languageDB.getLanguageName());
		
		if(languageDB.getBooks() != null) {
			List<Book> books = new LinkedList<Book>();
			for(BookDB b: languageDB.getBooks()) {
				Book book = bookRepository.getOne(b.getId());
				books.add(book);
			}
			
			language.setBooks(books);
		}
	}
	
}
