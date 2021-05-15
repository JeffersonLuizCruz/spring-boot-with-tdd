package com.tdd.services.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tdd.entities.Person;
import com.tdd.repositories.PersonRepository;
import com.tdd.services.PersonService;
import com.tdd.services.exceptions.BadRequestException;

@Service
public class PersonServiceImpl implements PersonService {

	private PersonRepository personRepository;

	public PersonServiceImpl(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	@Override
	public Person save(Person person) {
		Optional<Person> result = personRepository.findByCpf(person.getCpf());
		
		if(result.isPresent())
			throw new BadRequestException("CPF existente");
		
		return personRepository.save(person);
	}

}
