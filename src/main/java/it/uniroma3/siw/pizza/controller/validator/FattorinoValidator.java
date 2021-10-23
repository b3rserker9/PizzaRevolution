package it.uniroma3.siw.pizza.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.pizza.model.Fattorino;
import it.uniroma3.siw.pizza.service.FattorinoService;

@Component
public class FattorinoValidator implements Validator{

	@Autowired
	private FattorinoService fattorinoservice;

	@Override
	public boolean supports(Class<?> clazz) {
		return Fattorino.class.equals(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {
		Fattorino fattorino = (Fattorino)o;
		
		String nome = fattorino.getNome().trim();
		String telefono = fattorino.getTelefono().trim();
		
		if(nome.isEmpty())
			errors.rejectValue("nome", "required");
		else if(fattorinoservice.alreadyExists(nome))
			errors.reject("fattorinoDuplicate");
		
		if(telefono.isEmpty())
			errors.rejectValue("telefono", "required");

	}
	

}
