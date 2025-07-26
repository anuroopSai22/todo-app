package com.anuroop.myFirstWebapp.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import com.anuroop.myFirstWebapp.model.Todo;

import jakarta.validation.Valid;

@Service
public class TodoService {
	
private static List<Todo> todos = new ArrayList<Todo>();
	
	public static int todoCount=0;
	static {
		todos.add(new Todo(++todoCount, "anuroop","Learn AWS", 
							LocalDate.now().plusYears(1), false ));
		todos.add(new Todo(++todoCount, "anuroop","Learn DevOps", 
				LocalDate.now().plusYears(2), false ));
		todos.add(new Todo(++todoCount, "anuroop","Learn Full Stack Development", 
				LocalDate.now().plusYears(3), false ));
	}
	
	public List<Todo> getTodobyUsername(String username){
		Predicate<? super Todo>predicate = todo->todo.getUsername().equalsIgnoreCase(username);
		return todos.stream().filter(predicate).toList();
	}
	
	public void addTodo(String username, String description, LocalDate localdate, boolean done) {
		Todo todo = new Todo(++todoCount, username, description, localdate, done);
		todos.add(todo);
	}
	
	public void deleteById(int id) {
		Predicate<? super Todo> predicate = todo->todo.getId()==id;
		todos.removeIf(predicate);
		
	}
	
	public Todo findById(int id) {
		Predicate<? super Todo> predicate = todo->todo.getId()==id;
		Todo todo = todos.stream().filter(predicate).findFirst().get();
		return todo;
	}

	public void updateTodo(@Valid Todo todo) {
		deleteById(todo.getId());
		todos.add(todo);
		
	}
}
