package br.com.caelum.tarefas.controller;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.caelum.exception.DaoException;
import br.com.caelum.exception.JdbcConnectionException;
import br.com.caelum.jdbc.dao.GenericDao;
import br.com.caelum.tarefas.modelo.Tarefa;

@Controller
public class TarefasController {

	private GenericDao<Tarefa> dao;
	
	public TarefasController() {
		dao = new GenericDao<Tarefa>();
	}
	
	@RequestMapping("novaTarefa")
	public String form() {
		return "tarefa/formulario";
	}
	
	@RequestMapping("adicionaTarefa")
	public String adiciona(@Valid Tarefa tarefa, BindingResult result, HttpServletRequest request) throws JdbcConnectionException, DaoException {
		
		if(result.hasErrors())
		{
			return "tarefa/formulario";
		}
		
		dao.setConnection((Connection) request.getAttribute("connection"));		
		dao.save(tarefa);		
		return "tarefa/adicionada";
		
	}
	
}
