package com.radosav.master.rad.dto;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import com.radosav.master.rad.model.Book;
import com.radosav.master.rad.model.Genre;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;

import lombok.Setter;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Setter
@Getter
@JsonInclude(value = Include.NON_NULL)

public class GenreDB implements Serializable {
	
	private static final long serialVersionUID = -308323301543707632L;
	
	private Integer id;

	private String genreName;
	
	private List<BookDB> books;
	
	public GenreDB(Genre ent) {
		this(ent, false);
	}
	
	public GenreDB(Genre ent, boolean includeFKs) {
		this.id = ent.getId();
		this.genreName = ent.getGenreName();
		
		if(includeFKs) {
			books = new LinkedList<BookDB>();
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
		return "GenreDB [id=" + id + ", genreName=" + genreName + ", books=" + books + "]";
	}
}
