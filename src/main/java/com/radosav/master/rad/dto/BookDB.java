package com.radosav.master.rad.dto;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.radosav.master.rad.model.Author;
import com.radosav.master.rad.model.Book;
import com.radosav.master.rad.model.Genre;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;

import lombok.Setter;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Setter
@Getter
@JsonInclude(value = Include.NON_NULL)
public class BookDB implements Serializable {
	
	private static final long serialVersionUID = 457732658964050089L;
	
	private Integer id;
	
	private String bookName;
	
	private String isbnNumber;
	
	private String bookDescription;
	
	private Set<AuthorDB> authors;
	
	private LanguageDB language;
	
	private Set<GenreDB> genres;
	
	private UserDB user;
	
	public BookDB(Book ent) {
		this(ent, false);
	}
	
	public BookDB(Book ent, boolean includeFKs) {
		this.id = ent.getId();
		this.bookDescription = ent.getBookDescription();
		this.isbnNumber = ent.getIsbnNumber();
		this.bookName = ent.getBookName();
		
		if(includeFKs) {
			user = new UserDB(ent.getUser());
			
			authors = new LinkedHashSet<AuthorDB>();
			for(Author a: ent.getAuthors()) {
				AuthorDB author = new AuthorDB(a);
				
				if(author != null) {
					authors.add(author);
				}
			}
			
			language = new LanguageDB(ent.getLanguage());
			
			genres = new LinkedHashSet<GenreDB>();
			for(Genre g: ent.getGenres()) {
				GenreDB genre = new GenreDB(g);
				
				if(genre != null) {
					genres.add(genre);
				}
			}
		}
	}

	@Override
	public String toString() {
		return "BookDB [id=" + id + ", bookName=" + bookName + ", isbnNumber=" + isbnNumber + ", bookDescription="
				+ bookDescription + ", authors=" + authors + ", languages=" + language + ", genres=" + genres
				+ ", user=" + user + "]";
	}
	
	
}
