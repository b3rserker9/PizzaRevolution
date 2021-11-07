package it.uniroma3.siw.pizza.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.pizza.model.OrdinePizza;
import it.uniroma3.siw.pizza.model.Pizza;
import it.uniroma3.siw.pizza.repository.OrdinePizzaRepository;

@Service
public class OrdinePizzaService {

	@Autowired
	private OrdinePizzaRepository ordinepizzarepository;
	
	@Transactional
	public OrdinePizza inserisci(OrdinePizza ordinepizza) {
		return ordinepizzarepository.save(ordinepizza);
	}
	
	public int quantita(Long id) {
		return ordinepizzarepository.findByPizza(id);
	}
	
	
}
