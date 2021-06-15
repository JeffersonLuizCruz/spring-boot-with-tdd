package com.tdd.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.tdd.entities.Person;
import com.tdd.entities.Phone;
import com.tdd.repositories.PersonRepository;
import com.tdd.services.PersonService;
import com.tdd.services.exceptions.BadRequestException;
import com.tdd.services.impl.PersonServiceImpl;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class PersonServiceTest {
	
	private static final String TEST = "123456789";
	
	private static final String NAME = "Jefferson";
	private static final String CPF = "123456789";
	private static final String DDD = "81";
	private static final String NUMBER = "888023521";
	
	@MockBean
	private PersonRepository personRepository;
	
	private PersonService personService;
	
	private Person person;
	private Phone phone;
	
	@BeforeEach
	void setUp() throws Exception {
		personService = new PersonServiceImpl(personRepository);
		
		person = new Person();
		this.person.setName(NAME);
		this.person.setCpf(CPF);
		this.person.setCpf(TEST);
		
		phone = new Phone();
		phone.setDdd(DDD);
		phone.setNumber(NUMBER);
		
		person.getPhones().add(phone);
		
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
		
		BadRequestException e = assertThrows(BadRequestException.class, () -> personService.save(person));
		assertEquals("Já existe pessoa cadastrada com o CPF '" + CPF + "'", e.getMessage());
		
		//Método com mesma utilidade
//		Mockito.doThrow(BadRequestException.class).when(personRepository).findByCpf(CPF);
//		personService.save(person);
	}
	
	@Test
	@DisplayName("Não deve salvar pessoas com mesmo número de telefone")
	public void error_add_identical_phone() {
		Mockito.when(personRepository.findByPhoneDddAndPhoneNumber(DDD, NUMBER)).thenReturn(Optional.of(person));
		
		assertThrows(BadRequestException.class, () -> personService.save(person));
	}
	

}
