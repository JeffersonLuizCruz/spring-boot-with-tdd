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
	public Person save(Person person){
		Optional<Person> entity = personRepository.findByCpf(person.getCpf());
		
		verifyIfExistCpf(person, entity);
		
		final String ddd = person.getPhones().get(0).getDdd();
		final String number = person.getPhones().get(0).getNumber();
		
		entity = personRepository.findByPhoneDddAndPhoneNumber(ddd, number);
		
		verifyIfExistPhone(entity);
		
		return personRepository.save(person);
	}

	private void verifyIfExistPhone(Optional<Person> entity) {
		if(entity.isPresent())
			throw new BadRequestException("Telefone cadastrado '" + entity.get().getCpf() + "'");
	}

	private void verifyIfExistCpf(Person person, Optional<Person> entity) {
		if(entity.isPresent())
			throw new BadRequestException("Já existe pessoa cadastrada com o CPF '" + person.getCpf() + "'");
	}

	@Override
	public Person findByPhone(String phone) {
		personRepository.findByPhoneDddAndPhoneNumber(phone, phone);
		return null;
	}

}
