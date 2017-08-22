package br.com.caelum.tarefas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.caelum.exception.DaoException;
import br.com.caelum.exception.JdbcConnectionException;
import br.com.caelum.jdbc.ConnectionFactory;
import br.com.caelum.jdbc.dao.GenericDao;
import br.com.caelum.jdbc.dao.IGenericDao;
import br.com.caelum.tarefas.modelo.Tarefa;

@Controller
public class TarefasController {

	@RequestMapping("novaTarefa")
	public String form() {
		return "tarefa/formulario";
	}
	
	@RequestMapping("adicionaTarefa")
	public String adiciona(Tarefa tarefa) throws JdbcConnectionException, DaoException {
		
		IGenericDao<Tarefa> dao = new GenericDao<Tarefa>(ConnectionFactory.getInstance());
		
		dao.save(tarefa);
		
		return "tarefa/adicionada";
		
	}
	
}
