package com.radosav.master.rad.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.radosav.master.rad.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
