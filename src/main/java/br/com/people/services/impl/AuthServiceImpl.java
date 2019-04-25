package br.com.people.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.people.enitities.User;
import br.com.people.exceptions.AuthenticationException;
import br.com.people.repositories.UserRepository;
import br.com.people.security.JwtSecurity;
import br.com.people.services.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

	private static final String WRONG_USERNAME_PASSWORD = "Wrong username or password!";

	@Autowired
	private UserRepository userRepository;

	@Override
	public String login(User user) throws AuthenticationException {
		User userFromDB = userRepository.findByEmail(user.getEmail());

		if (userFromDB == null || !userFromDB.getPassword().equals(user.getPassword()))
			throw new AuthenticationException(WRONG_USERNAME_PASSWORD);

		return JwtSecurity.userToJwt(userFromDB);
	}

	@Override
	public String register(User user) {
		userRepository.save(user);

		return JwtSecurity.userToJwt(user);
	}

}
