package com.radosav.master.rad.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.radosav.master.rad.controller.UserController;
import com.radosav.master.rad.dto.UserDB;

@RestController
@RequestMapping("/user")
public class UserRest {

	@Autowired
	private UserController controller;
	
	@GetMapping(value = "/get", produces = "application/json")
	public List<UserDB> getUsers(){
		List<UserDB> result = controller.getUsers();
		return result;
	}
	
	@GetMapping(value="/login", produces = "application/json")
	public UserDB logIn(@RequestParam String username, @RequestParam String password){
		return controller.logIn(username, password);
	}
	
	@PostMapping(value = "/save", consumes = "application/json", produces = "application/json")
	public UserDB saveUser(@RequestBody UserDB userDB) {
		return controller.saveUser(userDB);
	}

	@PostMapping(value = "/update", consumes = "application/json", produces = "application/json")
	public UserDB updateUser(@RequestBody UserDB userDB) {
		return controller.updateUser(userDB);
	}

	@GetMapping(value = "/remove")
	public void removeUser(@RequestParam Integer id) {
		controller.removeUser(id);
	}
}
