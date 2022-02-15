package it.uniroma3.siw.pizza.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Entity
public class Utente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private Long id;
	
	@Column
	private String nome;
	
	@Column
	private String cognome;
	
	@Column
	private String telefono;
	
	@OneToOne(cascade = CascadeType.ALL)
	private indirizzo indirizzo;
	
	@OneToMany (mappedBy = "cliente")
	private List<Ordine> ordini;
	
	public Utente () {
		this.ordini = new ArrayList<Ordine>();
	}
}
