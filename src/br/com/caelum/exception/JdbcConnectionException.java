package br.com.caelum.exception;

public class JdbcConnectionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4272941292405247423L;
	
	public JdbcConnectionException(String message, Exception e)
	{
		super(message,e);
	}
	
}
