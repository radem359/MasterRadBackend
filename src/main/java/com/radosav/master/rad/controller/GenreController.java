package com.radosav.master.rad.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.radosav.master.rad.dto.BookDB;
import com.radosav.master.rad.dto.GenreDB;
import com.radosav.master.rad.model.Book;
import com.radosav.master.rad.model.Genre;
import com.radosav.master.rad.repository.BookRepository;
import com.radosav.master.rad.repository.GenreRepository;

@Controller
@Transactional
public class GenreController {
	
	@Autowired
	private GenreRepository genreRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	public List<GenreDB> getGenres(){
		List<GenreDB> result = new LinkedList<GenreDB>();
		
		List<Genre> genres = genreRepository.findAll();
		for(Genre g: genres) {
			GenreDB genreDB = new GenreDB(g, true);
			result.add(genreDB);
		}
		
		return result;
	}
	
	public void removeGenre(Integer id) {
		Genre genre = genreRepository.getOne(id);
		
		if(genre.getBooks() != null) {
			for(Book b: genre.getBooks()) {
				b.getGenres().remove(genre);
				bookRepository.save(b);
			}
		}
		
		genreRepository.delete(genre);
	}
	
	public GenreDB saveGenre(GenreDB genreDB) {
		Genre genre = new Genre();
		setGenre(genre, genreDB);
		
		genreRepository.save(genre);
		genreDB.setId(genre.getId());
		
		if(genre.getBooks() != null) {
			for(Book b: genre.getBooks()) {
				b.getGenres().add(genre);
				bookRepository.save(b);
			}
		}
		
		return genreDB;
	}
	
	public GenreDB updateGenre(GenreDB genreDB) {
		Genre genre =	genreRepository.getOne(genreDB.getId());
		setGenre(genre, genreDB);
		
		genreRepository.save(genre);	
		
		if(genre.getBooks() != null) {
			List<Book> books = bookRepository.findAll();
			for(Book b: books) {
				if(!b.getGenres().contains(genre)) {
					if(genre.getBooks().contains(b)) {
						b.getGenres().add(genre);
					}
				}
				
				if(b.getGenres().contains(genre)) {
					if(!genre.getBooks().contains(b)) {
						b.getGenres().remove(genre);
					}
				}
				
				bookRepository.save(b);
			}
		}
		
		return genreDB;
	}
	
	private void setGenre(Genre genre, GenreDB genreDB) {
		genre.setGenreName(genreDB.getGenreName());

		if(genreDB.getBooks() != null) {
			List<Book> books = new LinkedList<Book>();
			for(BookDB b: genreDB.getBooks()) {
				Book book = bookRepository.getOne(b.getId());
				books.add(book);
			}
			
			genre.setBooks(books);
		}
	}
}
