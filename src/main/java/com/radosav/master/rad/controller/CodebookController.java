package com.radosav.master.rad.controller;

import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.radosav.master.rad.dto.AuthorDB;
import com.radosav.master.rad.dto.BookDB;
import com.radosav.master.rad.dto.GenreDB;
import com.radosav.master.rad.dto.LanguageDB;
import com.radosav.master.rad.dto.NationalityDB;
import com.radosav.master.rad.dto.UserDB;
import com.radosav.master.rad.model.Author;
import com.radosav.master.rad.model.Book;
import com.radosav.master.rad.model.Genre;
import com.radosav.master.rad.model.Language;
import com.radosav.master.rad.model.Nationality;
import com.radosav.master.rad.model.User;
import com.radosav.master.rad.repository.AuthorRepository;
import com.radosav.master.rad.repository.BookRepository;
import com.radosav.master.rad.repository.GenreRepository;
import com.radosav.master.rad.repository.LanguageRepository;
import com.radosav.master.rad.repository.NationalityRepository;
import com.radosav.master.rad.repository.UserRepository;

@Controller
@Transactional
public class CodebookController {
	
	@Autowired
	private AuthorRepository authorRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private NationalityRepository nationalityRepository;
	
	@Autowired
	private LanguageRepository languageRepository;
	
	@Autowired
	private GenreRepository genreRepository;

	@Autowired
	private UserRepository userRepository;
	
	public List<AuthorDB> getAuthors(){
		List<AuthorDB> result = new LinkedList<AuthorDB>();
		
		List<Author> authors = authorRepository.findAll();
		for(Author a: authors) {
			AuthorDB authorDB = new AuthorDB(a);
			result.add(authorDB);
		}
		
		return result;
	}
	
	public List<BookDB>	getBooks(){
		List<BookDB> result = new LinkedList<BookDB>();
		
		List<Book> books = bookRepository.findAll();
		for(Book b: books) {
			BookDB book = new BookDB(b);
			result.add(book);
		}
		return result;
	}
	
	public List<GenreDB> getGenres(){
		List<GenreDB> result = new LinkedList<GenreDB>();
		
		List<Genre> genres = genreRepository.findAll();
		for(Genre g: genres) {
			GenreDB genreDB = new GenreDB(g);
			result.add(genreDB);
		}
		
		return result;
	}

	public List<LanguageDB> getLanguages(){
		List<LanguageDB> result = new LinkedList<LanguageDB>();
		
		List<Language> languages = languageRepository.findAll();
		for(Language l: languages) {
			LanguageDB language = new LanguageDB(l);
			result.add(language);
		}
		
		return result;
	}

	public List<NationalityDB> getNationalities(){
		List<NationalityDB> result = new LinkedList<NationalityDB>();
		
		List<Nationality> nationalities = nationalityRepository.findAll();
		for(Nationality n : nationalities) {
			NationalityDB nationalityDB = new NationalityDB(n);
			result.add(nationalityDB);
		}
		
		return result;
	}

	public List<UserDB> getUsers(){
		List<UserDB> result = new LinkedList<UserDB>();
		
		List<User> users = userRepository.findAll();
		for(User u : users) {
			UserDB user = new UserDB(u);
			result.add(user);
		}
		
		return result;
	}
	
}
