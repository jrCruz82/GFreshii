package com.edbootcamp.controller;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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

	@RequestMapping(value = "/recipe", method = RequestMethod.GET)
	public ModelAndView navToRecipe(Model model) {
		return new ModelAndView("recipe");
	}
	
	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public ModelAndView navToAccount() {
		ModelAndView mView = new ModelAndView();
		JSONParser jParser = new JSONParser();
		
		String firstName = null;
		String lastName = null;
		String email = null;
		String userName = null;
			
		try {
			JSONObject jObject = null;
			try {
				jObject = (JSONObject) jParser.parse(new FileReader("C:\\Users\\jose.a.cruz\\eclipse\\workspaces\\EdBootcamp\\GFreshii\\src\\main\\java\\com\\edbootcamp\\outputs\\output.json"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			firstName = (String) jObject.get("firstName");
			lastName = (String) jObject.get("lastName");
			email = (String) jObject.get("email");
			userName = (String) jObject.get("userName");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		mView.addObject("firstName" ,firstName);
		mView.addObject("lastName" ,lastName);
		mView.addObject("email" ,email);
		mView.addObject("userName" ,userName);
		
		mView.setViewName("account");
		
		return mView;
	}

}
