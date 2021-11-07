package it.uniroma3.siw.pizza.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.pizza.model.Ordine;
import it.uniroma3.siw.pizza.model.OrdinePizza;
import it.uniroma3.siw.pizza.model.Pizza;

public interface OrdinePizzaRepository extends CrudRepository<OrdinePizza, Long>{
	
	@Query(value = "SELECT quantita FROM ordine_pizze WHERE pizze_id = ?1" , nativeQuery = true)
	public int findByPizza(Long id);

}
