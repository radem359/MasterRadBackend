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
import com.radosav.master.rad.model.Author;
import com.radosav.master.rad.model.Book;
import com.radosav.master.rad.model.Nationality;
import com.radosav.master.rad.repository.AuthorRepository;
import com.radosav.master.rad.repository.BookRepository;
import com.radosav.master.rad.repository.NationalityRepository;

@Controller
@Transactional
public class AuthorController {
	
	@Autowired
	private AuthorRepository authorRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private NationalityRepository nationalityRepository;
	
	public List<AuthorDB> getAuthors(){
		List<AuthorDB> result = new LinkedList<AuthorDB>();
		
		List<Author> authors = authorRepository.findAll();
		for(Author a: authors) {
			AuthorDB authorDB = new AuthorDB(a, true);
			result.add(authorDB);
		}
		
		return result;
	}
	
	public void removeAuthor(Integer id) {
		Author author = authorRepository.getOne(id);
		if(author.getBooks() != null) {
			for(Book b: author.getBooks()) {
				b.getAuthors().remove(author);
				bookRepository.save(b);
			}
		}
		
		authorRepository.delete(author);
	}
	
	public AuthorDB saveAuthor(AuthorDB authorDB) {
		Author author = new Author();
		setAuthor(author, authorDB);
		
		authorRepository.save(author);
		authorDB.setId(author.getId());
		
		if(author.getBooks() != null) {
			for(Book b: author.getBooks()) {
				b.getAuthors().add(author);
				bookRepository.save(b);
			}
		}
		
		return authorDB;
	}
	
	public AuthorDB updateAuthor(AuthorDB authorDB) {
		Author author = authorRepository.getOne(authorDB.getId());
		setAuthor(author, authorDB);

		authorRepository.save(author);
		
		if(author.getBooks() != null) {
			List<Book> books = bookRepository.findAll();
			for(Book b: books) {
				if(!b.getAuthors().contains(author)) {
					if(author.getBooks().contains(b)) {
						b.getAuthors().add(author);
					}
				}
				
				if(b.getAuthors().contains(author)) {
					if(!author.getBooks().contains(b)) {
						b.getAuthors().remove(author);
					}
				}
				
				bookRepository.save(b);
			}
		}
		
		return authorDB;
	}
	
	private void setAuthor(Author author, AuthorDB authorDB) {
		author.setAuthorName(authorDB.getAuthorName());	
		
		if(authorDB.getNationality() != null) {
			Nationality nationality = nationalityRepository
					.getOne(authorDB.getNationality().getId());
			author.setNationality(nationality);
		}
		
		if(authorDB.getBooks() != null) {
			Set<Book> books = new LinkedHashSet<Book>();
			for(BookDB b: authorDB.getBooks()) {
				Book book = bookRepository.getOne(b.getId());
				books.add(book);
			}
			
			author.setBooks(books);
		}
	}
	
}
