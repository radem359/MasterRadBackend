package com.radosav.master.rad.dto;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import com.radosav.master.rad.model.Book;
import com.radosav.master.rad.model.Language;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;

import lombok.Setter;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Setter
@Getter
@JsonInclude(value = Include.NON_NULL)
public class LanguageDB implements Serializable {
	
	private static final long serialVersionUID = 4057481573574733334L;
	
	private Integer id;

	private String languageName;

	private List<BookDB> books;
	
	public LanguageDB(Language ent) {
		this(ent, false);
	}
	
	public LanguageDB(Language ent, boolean includeFKs) {
		this.id = ent.getId();
		this.languageName = ent.getLanguageName();
		
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
		return "LanguageDB [id=" + id + ", languageName=" + languageName + ", books=" + books + "]";
	}
}
