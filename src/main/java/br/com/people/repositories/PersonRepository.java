package br.com.people.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.people.enitities.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {}
