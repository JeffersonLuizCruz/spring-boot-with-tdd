package com.tdd.services;

import com.tdd.entities.Person;

public interface PersonService {

	Person save(Person person);
	Person findByPhone(String phone);

}
