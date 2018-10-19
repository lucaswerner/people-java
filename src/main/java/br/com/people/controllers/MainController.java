package br.com.people.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.people.enitities.Person;
import br.com.people.repositories.PersonRepository;
import br.com.people.services.PersonService;

@Controller
@CrossOrigin 
@RequestMapping(path="/people")
public class MainController {
	
	@Autowired
	private PersonService personService;
	
	@GetMapping
	public @ResponseBody ResponseEntity<List<Person>> getAllPersons() {
		return ResponseEntity.status(HttpStatus.OK).body(personService.listPersons());
	}
	
	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<Person> getPerson(@PathVariable("id") long id) {
		return ResponseEntity.status(HttpStatus.OK).body(personService.getPerson(id));
	}

	@PostMapping
	public @ResponseBody ResponseEntity<Person> addNewPerson (@RequestBody Person person) {
		return ResponseEntity.status(HttpStatus.CREATED).body(personService.savePerson(person));
	}
	
	@PutMapping
	public @ResponseBody ResponseEntity<Person> updatePerson (@RequestBody Person person) {
		Person updatedPerson = personService.updatePerson(person);
		return ResponseEntity
				.status(updatedPerson == null ? HttpStatus.NOT_ACCEPTABLE : HttpStatus.OK)
				.body(updatedPerson);
	}
	
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<Person> deletePerson (@PathVariable("id") long id) {
		return ResponseEntity
				.status(personService.deletePerson(id) ? HttpStatus.NO_CONTENT : HttpStatus.NOT_ACCEPTABLE)
				.body(null);
	}
}
