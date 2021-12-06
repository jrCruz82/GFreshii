package com.edbootcamp.controller;

import java.util.List;

import org.apache.log4j.LogManager;  
import org.apache.log4j.Logger; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.edbootcamp.api.views.Recipe;
import com.edbootcamp.restManagers.RESTRecipeManagerImpl;
import com.edbootcamp.view.RecipeImpl;




@Controller
@RequestMapping("user/recipes/")
public class RecipeController {

	private static Logger logger = LogManager.getLogger(RecipeController.class);

	@Autowired
	private RESTRecipeManagerImpl restManager;
	
	@GetMapping(value = "/{id}",headers = "Accept=application/json" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Recipe>> findAllRecipes(@PathVariable("id") Long id) {
        List<Recipe> recipes =  restManager.allRecipes(id);
        logger.info("Looking for recipes in restmanager after returning from backend");
        return new ResponseEntity<List<Recipe>>(recipes, HttpStatus.OK);
    }
	//add new recipe
	@PostMapping(value = "/createRecipe/{id}")
    public ResponseEntity<Recipe> createRecipe(@PathVariable("id") Long id, @RequestBody RecipeImpl recipe) {
		logger.info("Creating Recipe ");
		System.out.println(recipe+"\n\n");
		System.out.println(id);
		Recipe recipeView = restManager.saveRecipe(recipe,id);
        return new ResponseEntity<Recipe>(recipeView, HttpStatus.CREATED);
    }
	
	@PutMapping(value = "/updateRecipe/{id}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable("id") Long id ,@RequestBody RecipeImpl recipe) {
        logger.info("Updating recipe name ");
        Recipe recipeView = restManager.updateRecipe(recipe);
        return new ResponseEntity<Recipe>(recipeView, HttpStatus.CREATED);
    }
    
    @DeleteMapping(value = "/deleteRecipe/{id}")
    public ResponseEntity<?> deleteRecipe(@PathVariable("id") Long id) {
    	logger.info("Fetching & Deleting Recipe ");
        restManager.deleteRecipeById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @DeleteMapping(value = "/deleteInstruction/{id}")
    public ResponseEntity<?> deleteInstruction(@PathVariable("id") Long id) {
    	logger.info("Fetching & Deleting Recipe instructions");
        restManager.deleteRecipeInstructionById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	
    @RequestMapping(value = "/addInstruction/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Recipe> addInstruction(@PathVariable("id") Long id ,@RequestBody RecipeImpl recipe) {
        logger.info("Updating recipe name");
        Recipe recipeView = restManager.addInstruction(id, recipe);
        return new ResponseEntity<Recipe>(recipeView, HttpStatus.CREATED);
    }
    
}
