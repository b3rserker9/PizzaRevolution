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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import it.uniroma3.siw.pizza.model.Credentials;
import it.uniroma3.siw.pizza.model.Fattorino;
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
	
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("loginError", true);
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
	@RequestMapping(value = "/contattaciFree", method = RequestMethod.GET)
	public String contattaciFree(Model model) {
		return "contattaciFree";}
	
	
	
	
	@RequestMapping(value = "/default/ordine", method = RequestMethod.GET)
		public String ordine(Model model) {
		model.addAttribute("tuttePizze", pizzaservice.tutte());
		
		model.addAttribute("ordine",new Ordine());
		model.addAttribute("Ingredienti",this.ingredienteservice.tutti());
		model.addAttribute("role",this.credentialsService.getRoleAuthenticated());
		return "/default/ordine";
	}
	
	@RequestMapping(value="/default/ordine", method = RequestMethod.POST)
	public String newOrdine(@ModelAttribute("ordine") Ordine ordine, Model model) {
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
	public String newPizza(@ModelAttribute("pizza") Pizza pizza, @RequestParam("file") MultipartFile file, Model model) throws IllegalStateException, IOException {
		this.pizzaservice.inserisci(pizza);
		String baseDir="C:\\Users\\utente\\Documents\\workspace-spring-tool-suite-4-4.11.1.RELEASE\\PizzaRevolution\\src\\main\\resources\\static\\images\\";
			file.transferTo(new File(baseDir + pizza.getNome()+".jpg"));
		this.pizzaservice.inserisci(pizza);
		return "admin/home";
	}
	
	
	@GetMapping(value = "/menu")
	public String menu(Model model) {
		model.addAttribute("role",false);
		model.addAttribute("Pizze",this.pizzaservice.tutte());
		model.addAttribute("pizza" , new Pizza());
		return "menu";
	}
	@GetMapping(value = "/admin/menu")
	public String menuadmin(Model model) {
		model.addAttribute("role",true);
		model.addAttribute("Pizze",this.pizzaservice.tutte());
		model.addAttribute("pizza" , new Pizza());
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
	
	@GetMapping(value = "/admin/storico")
	public String storico(Model model) {
		double pippo = 0;
		for(Ordine o : ordineservice.tutti()) {
			System.out.println(o.getTotale());
			pippo+=o.getTotale();
		}
		model.addAttribute("ordine",ordineservice.tutti());
		model.addAttribute("pippo",pippo);		
		return "admin/StoricoOrdini";
	}
	
	@GetMapping(value = "/admin/det_ordine/{id}")
	public String det_ordine(@PathVariable("id") Long id, Model model) {
		Ordine ordine = ordineservice.ordinePerId(id);
		model.addAttribute("ordine",ordine);

		return "/admin/ordine";
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