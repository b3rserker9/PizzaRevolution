package it.uniroma3.siw.pizza.model;


import java.util.ArrayList;

import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Entity
public class Ordine {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter (value = AccessLevel.NONE)
	private Long id;
	
	@Column
	private String data;
	
	@Column
	private String orario;
	
	@Column
	private String note;
	
	@ManyToOne
	private Utente cliente;
	
	@ManyToOne
	private Fattorino fattorino;
		
	@OneToMany
	private List<Pizza> pizze;
	
	
	public Ordine() {
		this.pizze = new ArrayList<Pizza>();
	}
	
	@Transient
	public double getTotale() {
		double tot=00.00;
		List<Pizza> pizze = this.pizze;
		for(Pizza p : pizze){
			tot += p.getPrezzo();
		}
		return tot;
	}
	

}
