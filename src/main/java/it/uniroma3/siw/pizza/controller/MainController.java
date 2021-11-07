package it.uniroma3.siw.pizza.controller;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.pizza.controller.validator.FattorinoValidator;
import it.uniroma3.siw.pizza.controller.validator.PizzaValidator;
import it.uniroma3.siw.pizza.model.Credentials;
import it.uniroma3.siw.pizza.model.Ordine;
import it.uniroma3.siw.pizza.model.OrdinePizza;
import it.uniroma3.siw.pizza.model.Pizza;
import it.uniroma3.siw.pizza.model.Utente;
import it.uniroma3.siw.pizza.service.CredentialsService;
import it.uniroma3.siw.pizza.service.FattorinoService;
import it.uniroma3.siw.pizza.service.OrdinePizzaService;
import it.uniroma3.siw.pizza.service.OrdineService;
import it.uniroma3.siw.pizza.service.PizzaService;





@Controller
public class MainController {
	
	@Autowired
	private OrdinePizzaService ordinepizzaservice;
	
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
	private PizzaService pizzaservice;
	
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model) {
			return "index";
	}
	@RequestMapping(value = "/chisiamo", method = RequestMethod.GET)
	public String chiSiamo(Model model) {
			return "index";
	}
	@RequestMapping(value = "/contattaci", method = RequestMethod.GET)
	public String contattaci(Model model) {
		return "contattaci";
	}
	@RequestMapping(value = "/chisiamoFree", method = RequestMethod.GET)
	public String chiSiamoFree(Model model) {
			return "chisiamoFree";
	}
	@RequestMapping(value = "/contattaciFree", method = RequestMethod.GET)
	public String contattaciFree(Model model) {
		return "contattaciFree";}
		
	@RequestMapping(value = "/menu", method = RequestMethod.GET)
		public String menu(Model model) {
		model.addAttribute("Pizze", pizzaservice.tutte());
		System.out.println(pizzaservice.tutte());
		model.addAttribute("utente", this.getUtente());	
		Ordine ordine = new Ordine();
		OrdinePizza ordinepizza = new OrdinePizza();
		model.addAttribute("ordine", ordine);
		model.addAttribute("ordinepizza", ordinepizza);
			return "menuForm";
	}
	
	
	@RequestMapping(value="/ordine", method = RequestMethod.POST)
	public String newOrdine(@ModelAttribute("ordinepizza") OrdinePizza ordinepizza, @ModelAttribute("ordine") Ordine ordine, Model model) {
		ordine.setData(new SimpleDateFormat("dd/MMM/yyyy").format(new Date()));
		this.ordineservice.inserisci(ordine);
		this.ordinepizzaservice.inserisci(ordinepizza);
		
		return "home.html";
	}
	public Utente getUtente() {
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
		Utente utente = credentials.getUser();
		return utente;
	}

	@ModelAttribute("allOrari")
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
}