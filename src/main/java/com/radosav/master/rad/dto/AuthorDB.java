package com.radosav.master.rad.dto;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import com.radosav.master.rad.model.Author;
import com.radosav.master.rad.model.Book;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;

import lombok.Setter;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Setter
@Getter
@JsonInclude(value = Include.NON_NULL)
public class AuthorDB implements Serializable {
	
	private static final long serialVersionUID = 6145726323570844556L;
	
	private Integer id;
	
	private String authorName;
	
	private Set<BookDB> books;
	
	private NationalityDB nationality;
	
	public AuthorDB(Author ent) {
		this(ent, false);
	}
	
	public AuthorDB(Author ent, boolean includeFKs) {
		this.id = ent.getId();
		this.authorName = ent.getAuthorName();
		
		if(includeFKs) {
			nationality = new NationalityDB(ent.getNationality());
			
			books = new LinkedHashSet<BookDB>();
			for(Book b: ent.getBooks()) {
				BookDB book = new BookDB(b);
				
				if(book != null) {
					books.add(book);
				}
			}
		}
	}
	
	@Override
	public String toString() {
		return "AuthorDB [id=" + id + ", authorName=" + authorName + ", books=" + books + ", nationality=" + nationality
				+ "]";
	}
}
