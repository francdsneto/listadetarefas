package br.com.caelum.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import br.com.caelum.exception.DaoException;

public class GenericDao<T> implements IGenericDao<T> {

	private Connection connection = null;
	
	public GenericDao(Connection connection)
	{
		this.connection = connection;
	}

	@Override
	public T save(T entity) throws DaoException {
		
		PreparedStatement stmt = getInsertPreparedStatement(this.connection,entity);

		try 
		{
			stmt.execute();			
		} 
		catch (SQLException e) 
		{
			throw new DaoException("Erro ao salvar elemento - "+entity.getClass().getSimpleName(),e);
		}

		try 
		{
			stmt.close();
		} 
		catch (SQLException e) 
		{
			throw new DaoException("Erro ao fechar conexão com o banco - "+entity.getClass().getName(),e);
		}

		return null;
	}

	@Override
	public T update(T entity) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> getAll() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T getById(Long id) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) throws DaoException {
		// TODO Auto-generated method stub

	}

}
