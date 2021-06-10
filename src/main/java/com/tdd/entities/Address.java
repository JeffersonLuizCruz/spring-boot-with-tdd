package com.tdd.entities;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Address implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String street;
	private Integer number;
	private String complment;
	private String distract;
	private String city;
	private String state;
	

}
