package it.uniroma3.siw.pizza.repository;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.pizza.model.Pizza;

public interface PizzaRepository extends CrudRepository<Pizza, Long> {

	Optional<Pizza> findByNome(String nome);
	
}
