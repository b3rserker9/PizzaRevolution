package it.uniroma3.siw.pizza.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.pizza.model.Fattorino;
import it.uniroma3.siw.pizza.repository.FattorinoRepository;

@Service
public class FattorinoService {

	@Autowired
	private FattorinoRepository fattorinorepository;

	@Transactional
	public Fattorino inserisci(Fattorino f1) {
		return this.fattorinorepository.save(f1);
		
	}
	
	public List<Fattorino> tutti(){
		return (List<Fattorino>) this.fattorinorepository.findAll();
	}

	public Fattorino fattorinoPerId(Long id) {
		Optional<Fattorino> fattorino = this.fattorinorepository.findById(id);
		if(fattorino.isPresent())
			return fattorino.get();
		else
			return null;
	}
	
	public boolean alreadyExists(String nome) {
		Optional<Fattorino> fattorino = this.fattorinorepository.findByNome(nome);
		if(fattorino.isPresent())
			return true;
		else
			return false;

	}

	public void addFattorino(Fattorino fattorino) {
		this.fattorinorepository.save(fattorino);
	}

	public void elimina(Long id) {
		this.fattorinorepository.deleteById(id);
	}

	public Fattorino getFattorino(Long fattorinoId) {
		Optional<Fattorino> fattorino = this.fattorinorepository.findById(fattorinoId);
		return fattorino.orElse(null);
	}

}
