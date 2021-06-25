package com.radosav.master.rad.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.radosav.master.rad.dto.BookDB;
import com.radosav.master.rad.dto.UserDB;
import com.radosav.master.rad.model.Book;
import com.radosav.master.rad.model.User;
import com.radosav.master.rad.repository.BookRepository;
import com.radosav.master.rad.repository.UserRepository;

@Controller
@Transactional
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	public List<UserDB> getUsers(){
		List<UserDB> result = new LinkedList<UserDB>();
		
		List<User> users = userRepository.findAll();
		for(User u : users) {
			UserDB user = new UserDB(u, true);
			result.add(user);
		}
		
		return result;
	}
	
	public UserDB logIn(String username, String password) {
		User user = userRepository.findByUsernameAndPassword(username, password);
		if(user != null) {
			return new UserDB(user, true);
		}
		return null;
	}
	
	public void removeUser(Integer id) {
		User user = userRepository.getOne(id);
		userRepository.delete(user);
	}
	
	public UserDB saveUser(UserDB userDB) {
		User user = new User();
		setUser(user, userDB);
		
		userRepository.save(user);
		userDB.setId(user.getId());

		if(user.getBooks() != null) {
			for(Book b: user.getBooks()) {
				b.setUser(user);
				bookRepository.save(b);
			}
		}
		
		return userDB;
	}
	
	public UserDB updateUser(UserDB userDB) {
		User user = userRepository.getOne(userDB.getId());
		setUser(user, userDB);
		
		User admin = userRepository.findAdminUsers(true).get(0);
		
		userRepository.save(user);
		
		if(user.getBooks() != null) {
			List<Book> books = bookRepository.findAll();
			for(Book b: books) {
				if(!b.getUser().equals(user)) {
					if(user.getBooks().contains(b)) {
						b.setUser(user);
					}
				}
				
				if(b.getUser().equals(user)) {
					if(!user.getBooks().contains(b)) {
						if(admin != null) {
							b.setUser(admin);
						}
					}
				}
				
				bookRepository.save(b);
			}
		}
		
		return userDB;
	}
	
	private void setUser(User user, UserDB userDB) {
		user.setUsername(userDB.getUsername());
		user.setPassword(userDB.getPassword());
		user.setIsAdmin(userDB.getIsAdmin());
		
		if(userDB.getBooks() != null) {
			List<Book> books = new LinkedList<Book>();
			for(BookDB b: userDB.getBooks()) {
				Book book = bookRepository.getOne(b.getId());
				books.add(book);
			}
			
			user.setBooks(books);
		}
	}
	
}
