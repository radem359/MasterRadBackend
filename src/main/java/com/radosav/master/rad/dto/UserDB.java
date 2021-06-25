package com.radosav.master.rad.dto;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import com.radosav.master.rad.model.Book;
import com.radosav.master.rad.model.User;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;

import lombok.Setter;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Setter
@Getter
@JsonInclude(value = Include.NON_NULL)
public class UserDB implements Serializable {
	
	private static final long serialVersionUID = 6749216515232423726L;
	
	private Integer id;
	
	private String username;
	
	private String password;
	
	private Boolean isAdmin;
	
	private List<BookDB> books;
	
	public UserDB(User ent) {
		this(ent, false);
	}
	
	public UserDB(User ent, boolean includeFKs) {
		this.id	= ent.getId();
		this.username = ent.getUsername();
		this.password = ent.getPassword();
		this.isAdmin = ent.getIsAdmin();
		
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
		return "UserDB [id=" + id + ", username=" + username + ", password=" + password + ", isAdmin=" + isAdmin
				+ ", books=" + books + "]";
	}
}
