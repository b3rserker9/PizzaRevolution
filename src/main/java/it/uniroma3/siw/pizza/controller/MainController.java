package it.uniroma3.siw.pizza.controller;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import static it.uniroma3.siw.pizza.model.Credentials.ADMIN_ROLE;
import it.uniroma3.siw.pizza.controller.validator.IngredienteValidator;
import it.uniroma3.siw.pizza.controller.validator.PizzaValidator;
import it.uniroma3.siw.pizza.model.Credentials;
import it.uniroma3.siw.pizza.model.Fattorino;
import it.uniroma3.siw.pizza.model.Ingrediente;
import it.uniroma3.siw.pizza.model.Ordine;
import it.uniroma3.siw.pizza.model.Pizza;
import it.uniroma3.siw.pizza.model.Utente;
import it.uniroma3.siw.pizza.service.CredentialsService;
import it.uniroma3.siw.pizza.service.FattorinoService;
import it.uniroma3.siw.pizza.service.IngredienteService;
import it.uniroma3.siw.pizza.service.OrdineService;
import it.uniroma3.siw.pizza.service.PizzaService;



@Controller
public class MainController {
	@Autowired
	private OrdineService ordineservice;

	@Autowired
	private CredentialsService credentialsService;


	@Autowired
	private FattorinoService fattoriniService;
	
	@Autowired
	private PizzaService pizzaservice;
	
	@Autowired
	private IngredienteService ingredienteservice;
	
	@Autowired 
	private IngredienteValidator ingredientevalidator;
	
	@Autowired
	private PizzaValidator pizzavalidator;
	
	
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
	@RequestMapping(value = "/suppli", method = RequestMethod.GET)
	public String suppli(Model model) {
			return "suppli";
	}

	
	
	
	
	@RequestMapping(value = "/default/ordine", method = RequestMethod.GET)
		public String ordine(Model model) {
		model.addAttribute("tuttePizze", pizzaservice.tutte());
		System.out.println(this.ordineservice.getDisponibilita("19:15").getDisponibile());
		List<String> orari = new ArrayList<>();
		for(String s : this.ordineservice.populateOrari()) {
			if(this.ordineservice.getDisponibilita(s).getDisponibile() < this.fattoriniService.tutti().size()) {
				orari.add(s);
			}
		}
		model.addAttribute("orari", orari);
		model.addAttribute("ordine",new Ordine());
		model.addAttribute("Ingredienti",this.ingredienteservice.tutti());
		model.addAttribute("role",this.credentialsService.getRoleAuthenticated());
		return "/default/ordine";
	}
	
	@RequestMapping(value="/default/ordine", method = RequestMethod.POST)
	public String newOrdine(@ModelAttribute("ordine") Ordine ordine, Model model) {
		System.out.println(ordine.getOrario());
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
		Utente utente = credentials.getUser();
		ordine.setCliente(utente);
		boolean c=true;
		ordine.setData(new SimpleDateFormat("dd/MMM/yyyy").format(new Date()));
		ordine.setTotale(ordine.getTotale());
		for(Fattorino f : this.fattoriniService.tutti()) {
			if(f.getOrdini().isEmpty()) {
				ordine.setFattorino(f);
				 f.getOrdini().add(ordine);
				 break;
			}
			else {
			 for(Ordine o : f.getOrdini()) {
				 if(o.getOrario().equals(ordine.getOrario())) {
					 c=false;
					 break;
				 }
				 else {
					 c=true;
				 }
			 }
			 if(c) {
				 ordine.setFattorino(f);
				 f.getOrdini().add(ordine);
				 break;
			 }
			}
		}
		this.ordineservice.inserisci(ordine);
		return "/default/home";
	}
	
	
	
	@PostMapping(value = "/admin/newpizza")
	public String newPizza(@ModelAttribute("pizza") Pizza pizza, @RequestParam("file") MultipartFile file, Model model,BindingResult pizzaBindingResult) throws IllegalStateException, IOException {
		this.pizzavalidator.validate(pizza, pizzaBindingResult);
		if(!pizzaBindingResult.hasErrors()) {
		String baseDir="C:\\Users\\utente\\Documents\\workspace-spring-tool-suite-4-4.11.1.RELEASE\\PizzaRevolution\\src\\main\\resources\\static\\images\\";
			file.transferTo(new File(baseDir + pizza.getNome()+".jpg"));
		this.pizzaservice.inserisci(pizza);
		return "redirect:/admin/menu";
		}
		model.addAttribute("role",true);
		model.addAttribute("Pizze",this.pizzaservice.tutte());
		model.addAttribute("pizza" , new Pizza());
		model.addAttribute("ingrediente" , new Ingrediente());
		model.addAttribute("Ingredienti",this.ingredienteservice.tutti());
		return "menu";
	}
	@PostMapping(value = "/admin/newingrediente")
	public String newPizza(@ModelAttribute("ingrediente") Ingrediente ingrediente, Model model,BindingResult ingredientiBindingResult){
		this.ingredientevalidator.validate(ingrediente, ingredientiBindingResult);
		if(!ingredientiBindingResult.hasErrors()) {
		this.ingredienteservice.inserisci(ingrediente);
		return "redirect:/admin/menu";
		}
		model.addAttribute("role",true);
		model.addAttribute("Pizze",this.pizzaservice.tutte());
		model.addAttribute("pizza" , new Pizza());
		model.addAttribute("ingrediente" , new Ingrediente());
		model.addAttribute("Ingredienti",this.ingredienteservice.tutti());
		return "menu";
	}
	
	
	@GetMapping(value = "/menu")
	public String menu(Model model) {
		model.addAttribute("role",false);
		model.addAttribute("Pizze",this.pizzaservice.tutte());
		return "menu";
	}
	@GetMapping(value = "/admin/menu")
	public String menuadmin(Model model) {
		model.addAttribute("role",true);
		model.addAttribute("Pizze",this.pizzaservice.tutte());
		model.addAttribute("pizza" , new Pizza());
		model.addAttribute("ingrediente" , new Ingrediente());
		model.addAttribute("Ingredienti",this.ingredienteservice.tutti());
		return "menu";
	}
	
	@GetMapping(value = "/admin/fattorini")
	public String fattorini(Model model) {
		model.addAttribute("fattorini",fattoriniService.tutti());
		model.addAttribute("fattorino",new Fattorino());
		return "admin/fattorini";
	}
	
	@PostMapping(value = "/admin/newFattorino")
	public String newFattorino(@ModelAttribute("fattorino") Fattorino fattorino, Model model){
		
		fattoriniService.addFattorino(fattorino);
		
		return "redirect:/admin/fattorini";
	}
	
	@GetMapping(value = "/admin/eliminaFattorino/{id}")
    public String eliminaFattorino(@PathVariable("id") Long id) {
		
		this.fattoriniService.elimina(id);

        return "redirect:/admin/fattorini";
    }
	
	@PostMapping(value = "/admin/aggiornaFattorini")
    public String aggiornaUtenti(@RequestParam Long FattorinoId, Model model,@ModelAttribute("fattorino") Fattorino fattorino) {
		
		Fattorino fattorino_attuale = this.fattoriniService.getFattorino(FattorinoId);
		
		fattorino_attuale.setNome(fattorino.getNome());
		fattorino_attuale.setTelefono(fattorino.getTelefono());
		fattorino_attuale.setTipo_veicolo(fattorino.getTipo_veicolo());
		
		this.fattoriniService.addFattorino(fattorino_attuale);
		
        return "redirect:/admin/fattorini";
    }
	
	@GetMapping(value = "/admin/storico")
	public String storico(Model model) {
		model.addAttribute("role",true);
		model.addAttribute("ordine",ordineservice.tutti());	
		return "StoricoOrdini";
	}
	
	@GetMapping(value = "/default/storico")
	public String storicopersonale(Model model) {
		model.addAttribute("role",false);
		model.addAttribute("ordine",ordineservice.ordiniPerCliente(getUtente()));	
		return "StoricoOrdini";
	}
	
	@GetMapping(value = "/det_ordine/{id}")
	public String det_ordine(@PathVariable("id") Long id, Model model) {
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
		model.addAttribute("role",credentials.getRole());
		Ordine ordine = ordineservice.ordinePerId(id);
		model.addAttribute("ordine",ordine);

		return "det_ordine";
	}
	
	public double totaleordine(List<Ordine> ordini) {
		double totale=5;
		for(Ordine o : ordini) {
			totale=+o.getTotale();
		}
		return totale;
		
	}
	
	public Utente getUtente() {
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
		Utente utente = credentials.getUser();
		return utente;
	}

}