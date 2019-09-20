package com.carros.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {

	@GetMapping
	public String get() {
		return "API dos carros";
	}

	/*
	 * @RequestMapping(path = "/login", method = RequestMethod.POST) public String
	 * login(@RequestParam("login") String login, @RequestParam("password") String
	 * senha) { return "Login: " + login + ", Senha: " + senha; }
	 * 
	 * @GetMapping("/carros/{id}") public String getCarroById(@PathVariable("id")
	 * long id) { return "Carro by id: " + id; }
	 * 
	 * @GetMapping("/carros/tipo/{tipo}") public String
	 * getCarroByTipo(@PathVariable("tipo") String tipo) { return
	 * "Lista de Carros do tipo: " + tipo; }
	 * 
	 * @RequestMapping(method = RequestMethod.POST) public String post() { return
	 * "Post Spring Boot"; }
	 * 
	 * @RequestMapping(method = RequestMethod.PUT) public String put() { return
	 * "Put Spring Boot"; }
	 * 
	 * @RequestMapping(method = RequestMethod.DELETE) public String delete() {
	 * return "Delete Spring Boot"; }
	 */
}
