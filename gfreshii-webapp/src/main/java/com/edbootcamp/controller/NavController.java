package com.edbootcamp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NavController {

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView navToHome(Model model) {
		model.addAttribute(model);
		return new ModelAndView("home");
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView navToLogin(Model model) {
		model.addAttribute(model);
		return new ModelAndView("login");
	}

	@RequestMapping(value = "/recipe", method = RequestMethod.GET)
	public ModelAndView navToRecipe(Model model) {
		return new ModelAndView("recipe");
	}
	
	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public ModelAndView navToAccount(Model model) {
		model.addAttribute(model);
		return new ModelAndView("account");
	}

}
