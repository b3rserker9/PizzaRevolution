package it.uniroma3.siw.pizza.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.*;


@Entity
@Data public class Pizza {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private double prezzo;
	
	//ASSOCIAZIONI
	
	@ManyToMany
	private List<Ingrediente> ingredientiBase;
	
	//@ManyToMany
	//private List<Ingrediente> ingredienteAggiunta;
	
	public Pizza() {
		this.ingredientiBase = new ArrayList<Ingrediente>();
		//this.ingredienteAggiunta = new ArrayList<Ingrediente>();
	}
	
}