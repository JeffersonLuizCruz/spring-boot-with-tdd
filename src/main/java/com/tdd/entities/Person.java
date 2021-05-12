package com.tdd.entities;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter @Setter
public class Person implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String cpf;
	private List<Address> address;
	private List<Phone> phones;

}
