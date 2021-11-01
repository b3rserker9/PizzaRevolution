package it.uniroma3.siw.pizza.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.pizza.model.Pizza;
import it.uniroma3.siw.pizza.service.PizzaService;





@Controller
public class MainController {
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
			return "menuForm";
	}
}