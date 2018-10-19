package br.com.people.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.people.enitities.Person;
import br.com.people.repositories.PersonRepository;
import br.com.people.services.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository personRepository;
	
	@Override
	public List<Person> listPersons() {
		return (List<Person>) personRepository.findAll();
	}

	@Override
	public Person getPerson(Long id) {
		return personRepository.findById(id).orElse(null);
	}

	@Override
	public Person savePerson(Person person) {
		return personRepository.save(person);
	}

	@Override
	public Person updatePerson(Person person) {
		if (personRepository.existsById(person.getId()))
			return personRepository.save(person);
		
		return null;
	}

	@Override
	public boolean deletePerson(Long id) {
		if (personRepository.existsById(id)) {
			personRepository.deleteById(id);
			return true;
		}
		
		return false;
	}

}
