package com.edbootcamp.serviceController;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edbootcamp.entity.UserImpl;
import com.edbootcamp.service.UserService;

@RestController
@RequestMapping("user/")
public class UserController {

	private static Logger logger = LogManager.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	

	@PostMapping(value = "createUser")
    public ResponseEntity<UserImpl> createUser(@RequestBody UserImpl user) {
		logger.debug(user.getPassword());
		if (userService.isUserExist(user)) {
           logger.error("A user with that username already exist");
            return new ResponseEntity<UserImpl>( HttpStatus.NOT_IMPLEMENTED);
        }
		logger.info("Creating User " + user.getPassword());
		UserImpl userImpl = userService.saveUser(user);
        return new ResponseEntity<UserImpl>(userImpl, HttpStatus.CREATED);
    }
	
	@DeleteMapping(value = "deleteUser")
    public ResponseEntity<Boolean> deleteUser(@RequestBody UserImpl user) {
		
		if (!userService.isUserExist(user)) {
           logger.error("A user with that username does not exist");
            return new ResponseEntity<Boolean>( HttpStatus.NOT_IMPLEMENTED);
        }
		logger.info("Creating User " + user.getUserName());
		Boolean isDeleted = userService.deleteUser(user);
        return new ResponseEntity<Boolean>(isDeleted, HttpStatus.ACCEPTED);
    }
	
	@GetMapping(value = "userById-{id}")
    public ResponseEntity<UserImpl> getUserById(@PathVariable("id") Long id) {
		logger.info("Fetching user in the backend.");
        UserImpl user =  userService.userById(id);
        if(user==null){
        	logger.error("No user have been saved yet");
            return new ResponseEntity<UserImpl>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<UserImpl>(user,HttpStatus.OK);
    }
	
	@GetMapping(value = "getUser")
    public ResponseEntity<UserImpl> getUserByUsername(@RequestBody UserImpl user) {
		logger.info("Fetching user in the backend.");
        UserImpl userImpl =  userService.userByUsername(user.getUserName());
        if(userImpl==null){
        	logger.error("User does not exist");
            return new ResponseEntity<UserImpl>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<UserImpl>(userImpl,HttpStatus.OK);
    }
	
	@GetMapping()
    public ResponseEntity<List<UserImpl>> findAllUsers() {
		logger.info("Fetching list of recipes in the backend.");
        List<UserImpl> recipes =  userService.getAllUsers();
        if(recipes.isEmpty()){
        	logger.error("No Users have been saved yet");
            return new ResponseEntity<List<UserImpl>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<UserImpl>>(recipes,HttpStatus.OK);
    }
	
	@PostMapping(value = "login")
    public ResponseEntity<UserImpl> loginUser(@RequestBody UserImpl user) {
		
		UserImpl userImpl = userService.loginUser(user);
		System.out.println(userImpl);
		if (userImpl != null) {
			logger.debug("Welcome back, "+userImpl.getFirstName());
            return new ResponseEntity<UserImpl>(userImpl, HttpStatus.ACCEPTED);
        } else {
        	logger.error("Error:"+ "invalid credentials");
        	return new ResponseEntity<UserImpl>(HttpStatus.CONFLICT);
        }
		
    }
	
}
