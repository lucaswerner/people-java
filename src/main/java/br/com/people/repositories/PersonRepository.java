package br.com.people.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.people.enitities.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {}
