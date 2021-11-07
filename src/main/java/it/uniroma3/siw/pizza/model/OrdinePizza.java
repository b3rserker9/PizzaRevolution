package it.uniroma3.siw.pizza.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "ordine_pizze")
public class OrdinePizza {
	@EmbeddedId
	private OrdinePizzaId id;
 
	@ManyToOne
	@MapsId("ordineid")
	private Ordine ordine;
	
	@ManyToOne
	@MapsId("pizzeid")
	private Pizza pizze;
	
	@Column
	private int quantita;
}
