package com.anuroop.myFirstWebapp.repository;

import org.springframework.stereotype.Repository;
import com.anuroop.myFirstWebapp.model.Todo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface TodoRepository extends JpaRepository<Todo,Integer> {
	List<Todo> findByUsername(String username);
}
