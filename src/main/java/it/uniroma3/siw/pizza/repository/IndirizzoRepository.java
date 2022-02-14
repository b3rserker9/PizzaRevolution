package it.uniroma3.siw.pizza.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.pizza.model.indirizzo;

public interface IndirizzoRepository extends CrudRepository<indirizzo, Long>{

	Optional<indirizzo> findByVia(String via);
	
}
