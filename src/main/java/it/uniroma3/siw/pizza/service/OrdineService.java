package it.uniroma3.siw.pizza.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.pizza.model.Fattorino;
import it.uniroma3.siw.pizza.model.Occupazione;
import it.uniroma3.siw.pizza.model.Ordine;
import it.uniroma3.siw.pizza.model.Utente;
import it.uniroma3.siw.pizza.repository.OrdineRepository;

@Service
public class OrdineService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public List<String> populateOrari() {
	    List<String> orari = new ArrayList<String>();
	    orari.add("19:00");
	    orari.add("19:15");
	    orari.add("19:30");
	    orari.add("19:45");
	    orari.add("20:00");
	    orari.add("20:15");
	    orari.add("20:30");
	    orari.add("20:45");
	    orari.add("21:00");
	    orari.add("21:15");
	    orari.add("21:30");
	    orari.add("21:45");
	    orari.add("22:00");
	    return orari;
	}
	
	@Autowired
	private OrdineRepository ordinerepository;

	@Transactional
	public Ordine inserisci(Ordine ordine) {
		return ordinerepository.save(ordine);
	}

	public List<Ordine> ordiniPerCliente(Utente cliente){
		return ordinerepository.findByCliente(cliente);
	}

	public List<Ordine> tutti(){
		return ordinerepository.findByOrderByOrario();
	}
	
	public List<Ordine> ordiniDiOggiAncoraNonConsegnati(String data){
		return ordinerepository.findByDataAndFattorinoNullOrderByOrario(data);
	}
	
	public List<Ordine> ordiniPerFattorinoOggi(String data, Fattorino fattorino){
		return ordinerepository.findByDataAndFattorinoOrderByOrario(data, fattorino);
	}
	
	public List<Ordine> ordiniPerData(String data) {
		return ordinerepository.findByData(data);
	}
	
	public Ordine ordinePerId(Long id) {
		Optional <Ordine> ordine = this.ordinerepository.findById(id);
		if(ordine.isPresent())
			return ordine.get();
		else
			return null;
	}
	
	public Occupazione getDisponibilita(String ora) {
		return this.ordinerepository.findOccupazionePerOrario(ora);
	}
	








}
