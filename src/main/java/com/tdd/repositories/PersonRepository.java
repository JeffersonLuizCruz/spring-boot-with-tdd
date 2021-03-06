package com.tdd.repositories;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.tdd.entities.Person;

@Repository
public interface PersonRepository{

	Optional<Person> findByPhoneDddAndPhoneNumber(String ddd, String number);

	Person save(Person person);

	Optional<Person> findByCpf(String cpf);

}
