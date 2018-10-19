package br.com.people.services;

import java.util.List;

import br.com.people.enitities.Person;

public interface PersonService {

	List<Person> listPersons();
	
	Person getPerson(Long id);
	
	Person savePerson(Person person);
	
	Person updatePerson(Person person);
	
	boolean deletePerson(Long id);
}
