package it.uniroma3.siw.pizza.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.pizza.model.Pizza;
import it.uniroma3.siw.pizza.model.indirizzo;
import it.uniroma3.siw.pizza.repository.IndirizzoRepository;

@Service
public class IndirizzoService {

	@Autowired
	private IndirizzoRepository indirizzorepository;
	
	@Transactional
	public indirizzo inserisci(indirizzo indirizzo) {
		return this.indirizzorepository.save(indirizzo);
	}
}
