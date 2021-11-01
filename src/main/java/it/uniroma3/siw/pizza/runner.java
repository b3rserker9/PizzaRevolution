package it.uniroma3.siw.pizza;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import it.uniroma3.siw.pizza.model.Fattorino;
import it.uniroma3.siw.pizza.controller.validator.FattorinoValidator;
import it.uniroma3.siw.pizza.controller.validator.PizzaValidator;
import it.uniroma3.siw.pizza.model.Pizza;
import it.uniroma3.siw.pizza.service.CredentialsService;
import it.uniroma3.siw.pizza.service.FattorinoService;
import it.uniroma3.siw.pizza.service.OrdineService;
import it.uniroma3.siw.pizza.service.PizzaService;


@Service
public class runner  {
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
	
	/*@PostConstruct
	public void popola() {


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
