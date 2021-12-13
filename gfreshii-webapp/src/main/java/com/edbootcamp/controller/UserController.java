package com.edbootcamp.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edbootcamp.api.views.Recipe;
import com.edbootcamp.restManagers.RESTUserManagerImpl;
import com.edbootcamp.view.RecipeImpl;
import com.edbootcamp.view.UserImpl;


@Controller
@RequestMapping("user/")
public class UserController {

	private static Logger logger = LogManager.getLogger(UserController.class);
	
	@Autowired
	private RESTUserManagerImpl restManager;

	
	@PostMapping(value = "/createUser")
    public String createUser(UserImpl user, BindingResult result, ModelMap model) {
		logger.info("Creating user ");
		
		UserImpl userview = restManager.saveUser(user);
		if(userview==null) {
			model.addAttribute("home"," Sorry, try again. Username " + user.getUserName() + ", is taken.");
			return "home";
		}
		model.addAttribute("success"," Thank you for signing up " + userview.getFirstName() + ", you have successfully registered!");
		
        return "success";
    }
	
	@PostMapping("/login")
	public String login(@ModelAttribute("user") UserImpl user, Model model, HttpSession session) {
		UserImpl userImpl = restManager.loginStudent(user);
		System.out.println(userImpl);
		logger.debug(userImpl.getFirstName());
		if (userImpl != null) {
			logger.debug("Hello and Welcome, " + userImpl.getFirstName());
			model.addAttribute("user", userImpl);
			session.setAttribute("user", userImpl);
			return "recipe";
		}
		model.addAttribute("login", "Invalid Credentials");
		return "login";

	}
	
	@GetMapping("/login")
	public String loginDisplay(Model m, HttpSession session) {

		UserImpl user = new UserImpl();

		if (session.getAttribute("recipe") != null) {
			session.invalidate();
			m.addAttribute("success", "You have logout Successfully!!!");
		}
		m.addAttribute("recipe", user);
		return "login";
	}

	@GetMapping("/account")
	public String account(Model model) {
		return "account";
	}
	
	@PutMapping(value = "/recipes/updateUser/{id}")
    public ResponseEntity<UserImpl> updateUser(@PathVariable("id") Long id ,@RequestBody UserImpl user) {
        logger.info("Updating user");
        UserImpl userView = restManager.updateUser(user);
        return new ResponseEntity<UserImpl>(userView, HttpStatus.ACCEPTED);
    }
	
	@DeleteMapping(value = "/recipes/deleteUser/{id}")
	public ResponseEntity<Boolean> deleteUser(@PathVariable("id") Long id){
		logger.info("Deleting user");
		restManager.deleteUser(id);
		return new ResponseEntity<Boolean>( HttpStatus.ACCEPTED);
	}

}
