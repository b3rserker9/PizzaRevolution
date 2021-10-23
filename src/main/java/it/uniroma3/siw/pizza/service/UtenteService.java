package it.uniroma3.siw.pizza.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.pizza.model.Utente;
import it.uniroma3.siw.pizza.repository.UtenteRepository;

@Service
public class UtenteService {

    @Autowired
    protected UtenteRepository utenteRepository;

    @Transactional
    public Utente getUser(Long id) {
        Optional<Utente> result = this.utenteRepository.findById(id);
        return result.orElse(null);
    }

    /**
     * This method saves a User in the DB.
     * @param user the User to save into the DB
     * @return the saved User
     * @throws DataIntegrityViolationException if a User with the same username
     *                              as the passed User already exists in the DB
     */
    @Transactional
    public Utente saveUser(Utente utente) {
        return this.utenteRepository.save(utente);
    }

    @Transactional
    public List<Utente> getAllUsers() {
        List<Utente> result = new ArrayList<>();
        Iterable<Utente> iterable = this.utenteRepository.findAll();
        for(Utente utente : iterable)
            result.add(utente);
        return result;
    }
}
