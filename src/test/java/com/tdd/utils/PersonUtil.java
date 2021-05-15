package com.tdd.utils;

import com.tdd.entities.Person;

public class PersonUtil {
	
	static final String NOME = "Jefferson";
	static final String CPF = "123456789";
	
	public static Person personFakeEntity() {
		
		Person person = new Person();
		person.setName(NOME);
		person.setCpf(CPF);
		
		return person;
		
	}

}
