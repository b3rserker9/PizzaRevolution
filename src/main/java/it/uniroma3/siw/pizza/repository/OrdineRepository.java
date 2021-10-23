package it.uniroma3.siw.pizza.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.pizza.model.Fattorino;
import it.uniroma3.siw.pizza.model.Occupazione;
import it.uniroma3.siw.pizza.model.Ordine;
import it.uniroma3.siw.pizza.model.Utente;

public interface OrdineRepository extends CrudRepository<Ordine, Long>{
	
	public List<Ordine> findByOrario(String orario);
	
	public List<Ordine> findByCliente(Utente cliente);
	
	public List<Ordine> findByOrderByOrario();

	public List<Ordine> findByDataAndFattorinoNullOrderByOrario(String data);

	public List<Ordine> findByDataAndFattorinoOrderByOrario(String data, Fattorino fattorino);

	public List<Ordine> findByData(String data);
	
    @Query(value = "SELECT orario, COUNT(pizze_id) as nPizzePerOrario, COUNT( DISTINCT op.ordine_id) as nFattoriniPerOrario " +
    	   "FROM ordine_pizze op JOIN ordine o ON (op.ordine_id = o.id) " +
    		"WHERE o.data = ?1 " +
    	   "GROUP BY orario " +
    	   "ORDER BY orario ", nativeQuery = true)
	public Collection<Occupazione> findOccupazionePerOrario(String data);

}
