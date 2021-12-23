package com.example.microsReactivos.Controllers;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.microsReactivos.model.Persona;


import reactor.core.publisher.Flux;

@RestController
public class Controller {
	
	private List<Persona> listaUsuarios = List.of(new Persona("Raul", "RaulUser", "RaulPass"), new Persona("Antonio", "AntonioUser", "AntonioPass"), new Persona("Manuel", "ManuelUser", "ManuelPass"))
			.stream().collect(Collectors.toList());


	@GetMapping(path = "/login", produces = "text/event-stream")
	public Flux<Persona> numbers(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {
		
		var resultado = listaUsuarios.stream().filter(u -> u.getUserName().equals(username) && u.getPassword().equals(password)).collect(Collectors.toList());

		if(resultado.get(0) != null) {
			Flux<Persona> flux = Flux.fromIterable(listaUsuarios);
			return flux; 
		}
	
		return null; 
	}
	
	@GetMapping(path = "/addUser")
	public void add(@RequestParam(value = "name") String name, @RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {
		
		System.out.println(listaUsuarios.size());
		listaUsuarios.add(new Persona(name, username, password));
		
		
		System.out.println(listaUsuarios.size());
		
		
	
	}
}