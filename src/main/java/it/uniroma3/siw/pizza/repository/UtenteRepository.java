package it.uniroma3.siw.pizza.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.pizza.model.Utente;

public interface UtenteRepository extends CrudRepository<Utente, Long> {

}