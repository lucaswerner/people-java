package br.com.people.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.people.enitities.User;
import br.com.people.exceptions.AuthenticationException;
import br.com.people.services.AuthService;
import constants.UriConstants;

@Controller
@CrossOrigin
@RequestMapping(path = UriConstants.AUTH)
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping("login")
	public @ResponseBody ResponseEntity<String> getToken(@RequestBody User user) {

		try {

			return ResponseEntity.status(HttpStatus.OK).body(authService.login(user));

		} catch (AuthenticationException e) {

			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
		}
	}

	@PostMapping("register")
	public @ResponseBody ResponseEntity<String> registerUser(@RequestBody User user) {

		try {

			return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(user));
		} catch (DataIntegrityViolationException e) {

			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("User already registered!");
		}
	}
}
