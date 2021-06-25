package com.radosav.master.rad.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.radosav.master.rad.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

}
