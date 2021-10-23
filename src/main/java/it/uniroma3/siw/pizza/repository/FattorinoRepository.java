package it.uniroma3.siw.pizza.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.pizza.model.Fattorino;

public interface FattorinoRepository extends CrudRepository<Fattorino, Long>{

	Optional<Fattorino> findByNome(String nome);
	
	

}
