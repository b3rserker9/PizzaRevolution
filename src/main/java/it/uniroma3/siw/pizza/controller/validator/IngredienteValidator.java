package it.uniroma3.siw.pizza.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.pizza.model.Ingrediente;
import it.uniroma3.siw.pizza.service.IngredienteService;

@Component
public class IngredienteValidator implements Validator {
	
	@Autowired
	private IngredienteService ingredienteService;

	@Override
	public boolean supports(Class<?> clazz) {
		return Ingrediente.class.equals(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {
		Ingrediente ing = (Ingrediente)o;
		String nome = ing.getNome().trim();
		
		if(nome.isEmpty())
			errors.rejectValue("nome", "required");
		else if(ingredienteService.alreadyExists(nome))
			errors.rejectValue("nome", "duplicate");
	}

}
