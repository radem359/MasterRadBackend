package com.radosav.master.rad.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.radosav.master.rad.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, Integer> {

}
