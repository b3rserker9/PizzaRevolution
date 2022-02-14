package it.uniroma3.siw.pizza.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Entity
@Data
public class indirizzo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private Long id;
	
	@Column
	private String via;
	
	@Column
	private Long n_civico;
	
	@Column
	private Integer interno;
	
	@Column
	private Character scala;
	
	@Column
	private Long cap;
}
