package com.edbootcamp.controller;

import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.edbootcamp.api.views.Recipe;
import com.edbootcamp.restManagers.RESTRecipeManagerImpl;
import com.edbootcamp.restManagers.RESTUserManagerImpl;
import com.edbootcamp.view.RecipeImpl;
import com.edbootcamp.view.UserImpl;


@Controller
@RequestMapping("user/")
public class UserController {

	private static Logger logger = LogManager.getLogger(UserController.class);
	
	@Autowired
	private RESTUserManagerImpl restManager;
//	@SuppressWarnings("unchecked")
//	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
//	public ModelAndView addUser(@RequestParam("firstName") String firstName,
//								@RequestParam("lastName") String lastName,
//								@RequestParam("email") String email,
//								@RequestParam("userName") String userName,
//								HttpServletRequest request, HttpServletResponse response)
//	{
//		ModelAndView mv = new ModelAndView();
//		mv.setViewName("redirectPage");
//		mv.addObject("firstName", firstName);
//		mv.addObject("lastName", lastName);
//		mv.addObject("email", email);
//		mv.addObject("userName", userName);
//		
//		JSONObject jsonObj = new JSONObject();
//		
//		jsonObj.put("firstName", firstName);
//		jsonObj.put("lastName", lastName);
//		jsonObj.put("email", email);
//		jsonObj.put("userName", userName);
//		
//		try
//		{
//			FileWriter file = new FileWriter("C:\\Users\\jose.a.cruz\\eclipse\\workspaces\\EdBootcamp\\GFreshii\\src\\main\\java\\com\\edbootcamp\\outputs\\output.json");
//			file.write(jsonObj.toJSONString());
//			file.close();
//		}catch(IOException e) {
//			e.printStackTrace();
//		}
//		System.out.println("User created: " + jsonObj);
//		return mv;
//	}
	
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
	
	@GetMapping("/recipe")
	 public String loginDisplay(Model m,HttpSession session) {
	  
		UserImpl user = new UserImpl();
	  
	  if (session.getAttribute("recipe") != null) {
	   session.invalidate();
	   System.out.println("here");
	   m.addAttribute("success", "You have logout Successfully!!!");
	  }
	  m.addAttribute("recipe", user); 
	  return "login";  
	 }

}
