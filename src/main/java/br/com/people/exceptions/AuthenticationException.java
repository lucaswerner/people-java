package br.com.people.exceptions;

public class AuthenticationException extends Exception {

	private static final long serialVersionUID = -6498122094966084839L;

	public AuthenticationException(String error) {
		super(error);
	}
}
