package com.tdd.service;

import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.tdd.entities.Person;
import com.tdd.repositories.PersonRepository;
import com.tdd.services.PersonService;
import com.tdd.services.exceptions.BadRequestException;
import com.tdd.services.impl.PersonServiceImpl;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class PersonServiceTest {
	
	@MockBean
	private PersonRepository personRepository;
	private PersonService personService;
	
	private Person person;
	static final String NAME_TEST = null;
	static final String NAME = "Jefferson";
	static final String CPF = "123456789";
	
	@BeforeEach
	void setUp() throws Exception {
		personService = new PersonServiceImpl(personRepository);
		person = new Person();
		this.person.setName(NAME);
		this.person.setCpf(CPF);
		this.person.setCpf(NAME_TEST);
	}
	
	@Test
	@DisplayName("Deve salvar pessoa no repositório")
	public void saving_person_to_the_repository() {
		personService.save(person);
		verify(personRepository).save(person);
		
		Mockito.when(personRepository.findByCpf(CPF)).thenReturn(Optional.empty());
	}
	
	@Test
	@DisplayName("Não deve salvar pessoa com mesmo CPF")
	public void error_saving_identical_cpf(){
		Mockito.when(personRepository.findByCpf(CPF)).thenReturn(Optional.of(person));
		
		personService.save(person);	
		Assertions.assertThrows(BadRequestException.class, () -> personRepository.findByCpf(CPF));
	}
	

}
