package com.tdd.entities;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter @Setter
public class Phone implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String ddd;
	private String number;

}
