package br.com.santander.people;

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

@Controller
@CrossOrigin 
@RequestMapping(path="/people")
public class MainController {
	
	@Autowired
	private PersonRepository personRepository;
	
	@GetMapping
	public @ResponseBody Iterable<Person> getAllPersons() {
		return personRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public @ResponseBody Optional<Person> getPerson(@PathVariable("id") long id) {
		return personRepository.findById(id);
	}

	@PostMapping
	public @ResponseBody Person addNewPerson (@RequestBody Person person) {
		return personRepository.save(person);
	}
	
	@PutMapping
	public @ResponseBody ResponseEntity<Person> updatePerson (@RequestBody Person person) {
		if (personRepository.existsById(person.getId())) {
			return ResponseEntity.status(HttpStatus.OK).body(personRepository.save(person));
		}
		
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
	}
	
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<Person> deletePerson (@PathVariable("id") long id) {
		if (personRepository.existsById(id)) {
			personRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
	}
}
