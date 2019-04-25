package br.com.people.services.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.people.enitities.Person;
import br.com.people.repositories.PersonRepository;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceImplTest {
	
	private List<Person> persons = new ArrayList<Person>();

	@Mock
	PersonRepository personRepositoryMock;
	
	@InjectMocks
	PersonServiceImpl personServiceImpl;
	
	@Before
	public void setup() {
//		persons.add(new Person(0, "Fernando", "Silva"));
//		persons.add(new Person(0, "Roial", "Serelepe"));
//		persons.add(new Person(0, "Kleber", "Machado"));
	}
	
	@Test
	public void testListPersons() {
		when(personRepositoryMock.findAll()).thenReturn(persons);
		assertEquals(personServiceImpl.listPersons().size(), 3);
	}
}
