package com.edbootcamp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GroceryController {

	@RequestMapping(value = "/groceries", method = RequestMethod.GET)
	public ModelAndView groceryView(Model model) {
		
		model.addAttribute("message", "let's begin learning");
		return new ModelAndView("groceries");
	}

}
