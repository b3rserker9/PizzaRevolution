package it.uniroma3.siw.pizza.controller;

import org.springframework.beans.factory.annotation.Autowired;

import it.uniroma3.siw.pizza.controller.validator.PizzaValidator;
import it.uniroma3.siw.pizza.service.PizzaService;

public class PizzaController {
	@Autowired
	private PizzaService pizzaservice;

	@Autowired
	private PizzaValidator pizzavalidator;
	
}
