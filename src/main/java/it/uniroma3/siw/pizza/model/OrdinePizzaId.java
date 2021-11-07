package it.uniroma3.siw.pizza.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.Data;

@Embeddable
public class OrdinePizzaId implements Serializable {

	
	 @Column(name = "ordine_id")
	    private Long ordineid;
	 
	    @Column(name = "pizze_id")
	    private Long pizzeid;
	 
	 
	    public OrdinePizzaId(
	        Long ordineid,
	        Long pizzeid) {
	        this.ordineid = ordineid;
	        this.pizzeid = pizzeid;
	    }
	 
	
}
