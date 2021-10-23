package it.uniroma3.siw.pizza.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Entity
public class Fattorino {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private Long id;
	
	@Column
	private String nome;
	
	@Column
	private String telefono;
	
	@Column
	private String tipo_veicolo;
	
	@OneToMany
	private List<Ordine> ordini;
	
	public Fattorino() {
		this.ordini = new ArrayList<Ordine>();
	}
	
	@Transient
	public double totale;
}
