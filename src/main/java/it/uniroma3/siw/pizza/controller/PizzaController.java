package it.uniroma3.siw.pizza.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import it.uniroma3.siw.pizza.controller.validator.PizzaValidator;
import it.uniroma3.siw.pizza.model.Pizza;
import it.uniroma3.siw.pizza.service.PizzaService;

public class PizzaController {
	@Autowired
	private PizzaService pizzaservice;

	@Autowired
	private PizzaValidator pizzavalidator;
	
	
}
