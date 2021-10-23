package it.uniroma3.siw.pizza.controller.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.pizza.model.Ingrediente;
import it.uniroma3.siw.pizza.model.Pizza;
import it.uniroma3.siw.pizza.service.PizzaService;

@Component
public class PizzaValidator implements Validator{
	
	@Autowired
	private PizzaService pizzaservice;

	@Override
	public boolean supports(Class<?> clazz) {
		return Pizza.class.equals(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {
		Pizza pizza = (Pizza)o;
		
		String nome = pizza.getNome().trim();
		double prezzo = pizza.getPrezzo();
		List<Ingrediente> ingredientiBase = pizza.getIngredientiBase();
		
		if(nome.isEmpty())
			errors.rejectValue("nome", "required");
		else if(pizzaservice.alreadyExists(nome))
			errors.reject("pizzaDuplicate");
		
		if(prezzo == 0.0)
			errors.rejectValue("prezzo", "required");
		if(ingredientiBase.isEmpty())
			errors.rejectValue("ingredientiBase","required");
			
	}
	
	

}
