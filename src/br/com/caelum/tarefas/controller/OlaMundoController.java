package br.com.caelum.tarefas.controller;

import java.sql.Connection;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.caelum.jdbc.ConnectionFactory;

@Controller
public class OlaMundoController {

	@RequestMapping("/ola")
	public String execute() {
		Connection connection = ConnectionFactory.getInstance();
		System.out.println("Executando a lógica com Spring MVC");
		return "ok";
	}
	
}
