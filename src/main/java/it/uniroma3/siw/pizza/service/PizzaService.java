package it.uniroma3.siw.pizza.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.pizza.model.Pizza;
import it.uniroma3.siw.pizza.repository.PizzaRepository;

@Service
public class PizzaService {

	@Autowired
	private PizzaRepository pizzarepo;

	@Transactional
	public Pizza inserisci(Pizza pizza) {
		return this.pizzarepo.save(pizza);
	}

	public List<Pizza> tutte() {
		return (List<Pizza>)this.pizzarepo.findAll();
	}

	public boolean alreadyExists(String nome) {
		Optional<Pizza> pizza = this.pizzarepo.findByNome(nome);
		if(pizza.isPresent())
			return true;
		else
			return false;

	}
}
