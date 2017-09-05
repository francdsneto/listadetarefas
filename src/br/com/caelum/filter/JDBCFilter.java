package br.com.caelum.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import br.com.caelum.exception.JdbcConnectionException;
import br.com.caelum.jdbc.ConnectionFactory;

public class JDBCFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		try {
			Connection connection = ConnectionFactory.getInstance();
			req.setAttribute("connection", connection);
			System.out.println("Conexão aberta");
			chain.doFilter(req, resp);
			connection.close();
			System.out.println("Conexão fechada.");
		} catch (JdbcConnectionException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
