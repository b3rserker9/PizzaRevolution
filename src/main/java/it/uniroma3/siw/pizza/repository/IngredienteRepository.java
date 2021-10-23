package it.uniroma3.siw.pizza.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.pizza.model.Ingrediente;

	public interface IngredienteRepository extends CrudRepository<Ingrediente, Long>{

		Optional<Ingrediente> findByNome(String nome);
		
}
