package br.com.caelum.exception;

public class DaoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1930263947019651264L;

	public DaoException(String message, Exception e) {
		super(message,e);		
	}
	
}
