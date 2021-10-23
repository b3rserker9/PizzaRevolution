package it.uniroma3.siw.pizza.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.pizza.model.Ingrediente;
import it.uniroma3.siw.pizza.repository.IngredienteRepository;

@Service
public class IngredienteService {

	@Autowired
	private IngredienteRepository ingredienterepository;
	
	public Ingrediente inserisci(Ingrediente ingrediente) {
		return this.ingredienterepository.save(ingrediente);
	}

	public List<Ingrediente> tutti() {
		return (List<Ingrediente>) this.ingredienterepository.findAll();
	}
	
	public boolean alreadyExists(String nome) {
		Optional<Ingrediente> ing = this.ingredienterepository.findByNome(nome);
		if(ing.isPresent())
			return true;
		else
			return false;

	}
}
