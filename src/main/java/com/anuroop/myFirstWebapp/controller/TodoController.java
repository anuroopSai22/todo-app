package com.anuroop.myFirstWebapp.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.anuroop.myFirstWebapp.model.Todo;
import com.anuroop.myFirstWebapp.service.TodoService;

import jakarta.validation.Valid;

//@Controller
@SessionAttributes("name")
public class TodoController {
	
	private TodoService todoservice;
	
	public TodoController(TodoService service) {
		this.todoservice = service;
	}
	
	@RequestMapping("/list-todos")
	public String getAllTodos(ModelMap model){
		String username = getLoggingUsername(model);
		List<Todo> todos = todoservice.getTodobyUsername(username);
		for(Todo todo:todos) {
			System.out.println(todo);
		}
		model.put("todos", todos);
		return "listTodos";
	}
	
	@RequestMapping(value = "/add-Todo", method = RequestMethod.GET)
	public String showNewTodo(ModelMap model){
		String username = getLoggingUsername(model);
		Todo todo = new Todo(0, username, "", LocalDate.now().plusYears(1), false);
		model.put("todo",todo);
		return "addTodo";
	}


	
	@RequestMapping(value = "/add-Todo", method = RequestMethod.POST)
	public String addTodo(ModelMap model,@Valid Todo todo, BindingResult result){
		
		if(result.hasErrors()) {
			return "addTodo";
		}
		String username = getLoggingUsername(model);
		todoservice.addTodo(username, todo.getDescription(), todo.getTargetDate(),false);
		return "redirect:list-todos";
	}
	
	@RequestMapping("delete-todo")
	public String deleteTodo(@RequestParam int id){
		
		todoservice.deleteById(id);
		return "redirect:list-todos";
	}
	
	@RequestMapping(value="update-todo",method=RequestMethod.GET)
	public String showUpdateTodo(@RequestParam int id,ModelMap model) {
		Todo todo = todoservice.findById(id);
		model.addAttribute("todo", todo);
		return "addTodo";
		
	}
	
	@RequestMapping(value="update-todo",method=RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		if(result.hasErrors()) {
			return "addTodo";
		}
		
		String username = getLoggingUsername(model);
		todoservice.updateTodo(todo);
		return "redirect:list-todos";
	}
	
	private String getLoggingUsername(ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getName();
	}
	
}
