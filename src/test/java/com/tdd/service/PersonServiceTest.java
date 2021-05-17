package com.tdd.service;

import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.tdd.entities.Address;
import com.tdd.entities.Person;
import com.tdd.entities.Phone;
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
	private static final String TEST = "123456789";
	
	private static final String NAME = "Jefferson";
	private static final String CPF = "123456789";
	private static final String DDD = "81";
	private static final String NUMBER = "888023521";
	
	@BeforeEach
	void setUp() throws Exception {
		personService = new PersonServiceImpl(personRepository);
		
		person = new Person();
		this.person.setName(NAME);
		this.person.setCpf(CPF);
		this.person.setCpf(TEST);
		
		Phone phone = new Phone();
		phone.setDdd(DDD);
		phone.setNumber(NUMBER);
		
		person.getPhones().add(phone);
		
		//Mockito.when(personRepository.findByCpf(CPF)).thenReturn(Optional.empty());
	}
	
	@Test
	@DisplayName("Deve salvar pessoa no repositório")
	public void saving_person_to_the_repository() {
		personService.save(person);
		verify(personRepository).save(person);
				
	}
	
	@Test
	@DisplayName("Não deve salvar pessoa com mesmo CPF")
	public void error_add_identical_cpf() throws BadRequestException{
		Mockito.when(personRepository.findByCpf(CPF)).thenReturn(Optional.of(person));
		Mockito.doThrow(BadRequestException.class).when(personRepository).findByCpf(CPF);
		personService.save(person);
	}
	
	@Test
	@DisplayName("Não deve salvar pessoas com mesmo telefone")
	public void error_add_identical_phone() {
		Mockito.when(personRepository.findByDddAndPhone(DDD, NUMBER)).thenReturn(Optional.of(person));
		
		personService.save(person);
	}
	

}
