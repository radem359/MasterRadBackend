package com.radosav.master.rad.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.radosav.master.rad.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query(value = "SELECT u FROM User u WHERE u.isAdmin = ?1")
    List<User> findAdminUsers(boolean isAdmin);
	
	User findByUsernameAndPassword(String username, String password);

}
