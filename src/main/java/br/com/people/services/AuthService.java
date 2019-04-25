package br.com.people.services;

import br.com.people.enitities.User;
import br.com.people.exceptions.AuthenticationException;

public interface AuthService {

	public String login(User user) throws AuthenticationException;
	
	public String register(User user);
}
