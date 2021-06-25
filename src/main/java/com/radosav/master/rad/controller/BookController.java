package com.radosav.master.rad.controller;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.radosav.master.rad.dto.AuthorDB;
import com.radosav.master.rad.dto.BookDB;
import com.radosav.master.rad.dto.GenreDB;
import com.radosav.master.rad.model.Author;
import com.radosav.master.rad.model.Book;
import com.radosav.master.rad.model.Genre;
import com.radosav.master.rad.model.Language;
import com.radosav.master.rad.model.User;
import com.radosav.master.rad.repository.AuthorRepository;
import com.radosav.master.rad.repository.BookRepository;
import com.radosav.master.rad.repository.GenreRepository;
import com.radosav.master.rad.repository.LanguageRepository;
import com.radosav.master.rad.repository.UserRepository;

@Controller
@Transactional
public class BookController {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private AuthorRepository authorRepository;
	
	@Autowired
	private GenreRepository genreRepository;
	
	@Autowired
	private LanguageRepository languageRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public List<BookDB>	getBooks(){
		List<BookDB> result = new LinkedList<BookDB>();
		
		List<Book> books = bookRepository.findAll();
		for(Book b: books) {
			BookDB book = new BookDB(b, true);
			result.add(book);
		}
		return result;
	}
	
	public void removeBook(Integer id) {
		Book book = bookRepository.getOne(id);
		bookRepository.delete(book);
	}
	
	public BookDB saveBook(BookDB bookDB) {
		Book book = new Book();
		setBook(book, bookDB);
		
		bookRepository.save(book);
		bookDB.setId(book.getId());
		
		return bookDB;
	}
	
	public BookDB updateBook(BookDB bookDB) {
		Book book = bookRepository.getOne(bookDB.getId());
		setBook(book, bookDB);
		
		bookRepository.save(book);
		return bookDB;
	}
	
	private void setBook(Book book, BookDB bookDB) {
		book.setBookName(bookDB.getBookName());
		book.setIsbnNumber(bookDB.getIsbnNumber());
		book.setBookDescription(bookDB.getBookDescription());
		
		if(bookDB.getUser() != null) {
			User user = userRepository.getOne(bookDB.getUser().getId());
			book.setUser(user);
		}
		
		if(bookDB.getAuthors() != null) {
			Set<Author> authors = new LinkedHashSet<Author>();
			for(AuthorDB a : bookDB.getAuthors()) {
				Author author = authorRepository.getOne(a.getId());
				authors.add(author);
			}
			book.setAuthors(authors);
		}
		
		if(bookDB.getLanguage() != null) {
			Language language = languageRepository.getOne(bookDB.getLanguage().getId());
			book.setLanguage(language);
		}
		
		if(bookDB.getGenres() != null) {
			Set<Genre> genres = new LinkedHashSet<Genre>();
			for(GenreDB g : bookDB.getGenres()) {
				Genre genre = genreRepository.getOne(g.getId());
				genres.add(genre);
			}
			book.setGenres(genres);
		}
		
	}
	
}
