package it.uniroma3.siw.pizza;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import it.uniroma3.siw.pizza.model.Credentials;
import it.uniroma3.siw.pizza.model.Fattorino;
import it.uniroma3.siw.pizza.model.Ingrediente;
import it.uniroma3.siw.pizza.controller.validator.FattorinoValidator;
import it.uniroma3.siw.pizza.controller.validator.PizzaValidator;
import it.uniroma3.siw.pizza.model.Pizza;
import it.uniroma3.siw.pizza.model.Utente;
import it.uniroma3.siw.pizza.service.CredentialsService;
import it.uniroma3.siw.pizza.service.FattorinoService;
import it.uniroma3.siw.pizza.service.IngredienteService;
import it.uniroma3.siw.pizza.service.OrdineService;
import it.uniroma3.siw.pizza.service.PizzaService;
import it.uniroma3.siw.pizza.service.UtenteService;


@Service
public class runner  {
	
	@Autowired
	private UtenteService utenteservice;
	
	@Autowired
	private OrdineService ordineservice;

	@Autowired
	private CredentialsService credentialsService;

	@Autowired
	private PizzaService pizzaserv;

	@Autowired
	private PizzaValidator pizzavalidator;

	@Autowired
	private FattorinoService fattoriniService;
	@Autowired
	private FattorinoValidator fattorinovalidator;
	
	@Autowired
	private IngredienteService ingredienteservice;
	
	/*@PostConstruct
	public void popola() {
		
		Utente u = new Utente();
		Credentials c = new Credentials();
		u.setNome("Yousef");
		u.setCognome("Ali");
		u.setIndirizzo("Via giovanni aldini");
		c.setUser(u);
		c.setUsername("admin");
		c.setPassword("admin1");
		this.credentialsService.saveAdmin(c);
		this.utenteservice.saveUser(u);
		


		Pizza p1 = new Pizza();
		p1.setNome("Margherita");
		p1.setPrezzo(5.00);

		Pizza p2 = new Pizza();
		p2.setNome("Boscaiola");
		p2.setPrezzo(6.00);

		Pizza p3 = new Pizza();
		p3.setNome("Diavola");
		p3.setPrezzo(5.50);

		Pizza p4 = new Pizza();
		p4.setNome("4-Formaggi");
		p4.setPrezzo(7.00);

		Ingrediente i1 = new Ingrediente();
		i1.setNome("Pomodoro");
		
		Ingrediente i2 = new Ingrediente();
		i2.setNome("Mozzarella");
		
		Ingrediente i3 = new Ingrediente();
		i3.setNome("Salsiccia");
		
		Ingrediente i4 = new Ingrediente();
		i4.setNome("Funghi");
		
		Ingrediente i5 = new Ingrediente();
		i5.setNome("Salame piccante");
		
		Ingrediente i6 = new Ingrediente();
		i6.setNome("Provola");
		
		Ingrediente i7 = new Ingrediente();
		i7.setNome("Gongorzola");
		
		this.ingredienteservice.inserisci(i1);
		this.ingredienteservice.inserisci(i2);
		this.ingredienteservice.inserisci(i3);
		this.ingredienteservice.inserisci(i4);
		this.ingredienteservice.inserisci(i5);
		this.ingredienteservice.inserisci(i6);
		this.ingredienteservice.inserisci(i7);

		this.pizzaserv.inserisci(p1);
		this.pizzaserv.inserisci(p2);
		this.pizzaserv.inserisci(p3);
		this.pizzaserv.inserisci(p4);

		Fattorino f1 = new Fattorino();
		f1.setNome("Francesco");

		Fattorino f2 = new Fattorino();
		f2.setNome("Luca");

		Fattorino f3 = new Fattorino();
		f3.setNome("Marco");

		Fattorino f4 = new Fattorino();
		f4.setNome("Matteo");

		this.fattoriniService.inserisci(f1);
		this.fattoriniService.inserisci(f2);
		this.fattoriniService.inserisci(f3);
		this.fattoriniService.inserisci(f4);
		

	}*/
}
