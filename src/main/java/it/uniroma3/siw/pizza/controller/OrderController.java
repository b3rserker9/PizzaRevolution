package it.uniroma3.siw.pizza.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.pizza.controller.validator.FattorinoValidator;
import it.uniroma3.siw.pizza.controller.validator.PizzaValidator;
import it.uniroma3.siw.pizza.model.Credentials;
import it.uniroma3.siw.pizza.model.Ordine;
import it.uniroma3.siw.pizza.model.Utente;
import it.uniroma3.siw.pizza.service.CredentialsService;
import it.uniroma3.siw.pizza.service.FattorinoService;
import it.uniroma3.siw.pizza.service.OrdineService;
import it.uniroma3.siw.pizza.service.PizzaService;

public class OrderController {

}
