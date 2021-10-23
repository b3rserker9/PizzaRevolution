package it.uniroma3.siw.pizza.model;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
public class Ingrediente {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	//private Long prezzoAggiunto;
	
}