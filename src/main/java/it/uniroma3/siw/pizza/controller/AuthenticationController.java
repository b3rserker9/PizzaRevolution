package it.uniroma3.siw.pizza.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import it.uniroma3.siw.pizza.controller.validator.CredentialsValidator;
import it.uniroma3.siw.pizza.controller.validator.UtenteValidator;
import it.uniroma3.siw.pizza.model.Credentials;
import it.uniroma3.siw.pizza.model.Utente;
import it.uniroma3.siw.pizza.model.indirizzo;
import it.uniroma3.siw.pizza.service.CredentialsService;

@Controller
public class AuthenticationController {

	@Autowired
	private UtenteValidator userValidator;
	
	@Autowired
	private CredentialsValidator credentialsValidator;

	@Autowired
	private CredentialsService credentialsService;
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showRegisterForm (Model model) {
		model.addAttribute("user", new Utente());
		model.addAttribute("indirizzo", new indirizzo());
		model.addAttribute("credentials", new Credentials());
		return "registerUser";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET) 
	public String logout(Model model) {
		return "index";
	}
	
	
	//default lo abbiamo definito in authconfiguration una volta che il login ha avuto successo
    @RequestMapping(value = "/defaults", method = RequestMethod.GET)
    public String defaultAfterLogin(Model model) {
        
    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
    	Utente u = credentials.getUser();
    	model.addAttribute("user", u);
    	
    	//se e' admin
    	if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
            return "admin/home";
        }
    	//se non lo e'
        return "default/home";
    }
    @RequestMapping(value = { "/register" }, method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("user") Utente user,
                 BindingResult userBindingResult,
                 @ModelAttribute("credentials") Credentials credentials, @ModelAttribute("indirizzo") indirizzo indirizzo,
                 BindingResult credentialsBindingResult,
                 Model model) {

        this.userValidator.validate(user, userBindingResult);
        this.credentialsValidator.validate(credentials, credentialsBindingResult);

        if(!userBindingResult.hasErrors() && ! credentialsBindingResult.hasErrors()) {
        	user.setIndirizzo(indirizzo);
            credentials.setUser(user);
            credentialsService.saveCredentials(credentials);
            return "index";
        }
        return "registerUser";
    }
}
