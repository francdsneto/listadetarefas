package br.com.caelum.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import br.com.caelum.exception.DaoException;

public class GenericDao implements IGenericDao<Object> {

	private Connection connection = null;
	private Class<?> classe = null;
	
	public GenericDao(Connection connection)
	{
		this.connection = connection;
	}

	public void setClasse(Class<?> classe) {
		this.classe = classe;
	}

	@Override
	public Object save(Object entity) {

		PreparedStatement stmt = getInsertPreparedStatement(this.connection,classe,entity);

		try 
		{
			stmt.execute();			
		} 
		catch (SQLException e) 
		{
			throw new DaoException("Erro ao salvar elemento - "+classe.getName(),e);
		}

		try 
		{
			stmt.close();
		} 
		catch (SQLException e) 
		{
			throw new DaoException("Erro ao fechar conexão com o banco - "+classe.getName(),e);
		}

		return null;
	}

	@Override
	public Object update(Object entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

}
