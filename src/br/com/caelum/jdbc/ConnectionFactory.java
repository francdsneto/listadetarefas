package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.com.caelum.exception.JdbcConnectionException;

public final class ConnectionFactory {
	
	private static final String URL = "jdbc:mysql://localhost/fj22";
	private static final String USER = "root";
	private static final String PASSWORD =  "lol12345";
	private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";

	public static Connection getInstance() throws JdbcConnectionException {
		
		Connection connection = null;
		try {
			Class.forName(DRIVER_CLASS);
			connection = DriverManager.getConnection(URL,USER,PASSWORD);
		} catch (SQLException e) {
			throw new JdbcConnectionException("Erro ao realizar conexão com o banco.", e);
		} catch (ClassNotFoundException e) {
			throw new JdbcConnectionException("Driver de conexão não encontrado.", e);
		}
		
		return connection;
	}
	
}
