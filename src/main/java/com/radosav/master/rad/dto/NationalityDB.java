package com.radosav.master.rad.dto;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import com.radosav.master.rad.model.Author;
import com.radosav.master.rad.model.Nationality;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;

import lombok.Setter;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Setter
@Getter
@JsonInclude(value = Include.NON_NULL)
public class NationalityDB implements Serializable {
	
	private static final long serialVersionUID = 8217078635779576165L;
	
	private Integer id;
	
	private String nationalityName;
	
	private List<AuthorDB> authors;
	
	public NationalityDB(Nationality ent) {
		this(ent, false);
	}
	
	public NationalityDB(Nationality ent, boolean includeFKs) {
		this.id = ent.getId();
		this.nationalityName = ent.getNationalityName();
		
		if(includeFKs) {
			authors = new LinkedList<AuthorDB>();
			for(Author a: ent.getAuthors()) {
				AuthorDB author = new AuthorDB(a);
				
				if(author != null) {
					authors.add(author);
				}
			}
		}
	}

	@Override
	public String toString() {
		return "NationalityDB [id=" + id + ", nationalityName=" + nationalityName + ", authors=" + authors + "]";
	}
}
