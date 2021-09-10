package com.edbootcamp.controller;

import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class AddUserController {

	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public ModelAndView addUser(@RequestParam("firstName") String firstName,
								@RequestParam("lastName") String lastName,
								@RequestParam("email") String email,
								@RequestParam("userName") String userName,
								HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirectPage");
		mv.addObject("firstName", firstName);
		mv.addObject("lastName", lastName);
		mv.addObject("email", email);
		mv.addObject("userName", userName);
		
		JSONObject jsonObj = new JSONObject();
		
		jsonObj.put("firstName", firstName);
		jsonObj.put("lastName", lastName);
		jsonObj.put("email", email);
		jsonObj.put("userName", userName);
		
		try
		{
			FileWriter file = new FileWriter("C:\\Users\\jose.a.cruz\\eclipse\\workspaces\\EdBootcamp\\GFreshii\\src\\main\\java\\com\\edbootcamp\\outputs\\output.json");
			file.write(jsonObj.toJSONString());
			file.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		System.out.println("User created: " + jsonObj);
		
		
		return mv;
	}

}
