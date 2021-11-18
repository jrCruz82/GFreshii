package com.edbootcamp.controller;

import java.util.List;

import org.apache.log4j.LogManager;  
import org.apache.log4j.Logger; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
public class RecipeController {

	private static Logger LOGGER = LogManager.getLogger(RecipeController.class);

	@Autowired
	private RESTRecipeManagerImpl restManager;
	
	@GetMapping(value = "recipes/",headers = "Accept=application/json" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Recipe>> findAllRecipes() {
        List<Recipe> recipes =  restManager.allRecipes();
        LOGGER.info("Looking for recipes in restmanager after returning from backend");
        if(recipes.isEmpty()){
        	LOGGER.error("No Recipes have been saved yet");
            return new ResponseEntity<List<Recipe>>(HttpStatus.NO_CONTENT);
        }
        
        return new ResponseEntity<List<Recipe>>(recipes, HttpStatus.OK);
    }
	//add new recipe
	@PostMapping(value = "/recipes/createRecipe")
    public ResponseEntity<Recipe> createRecipe(@RequestBody RecipeImpl recipe) {
		LOGGER.info("Creating Recipe ");
		Recipe recipeView = restManager.saveRecipe(recipe);
        return new ResponseEntity<Recipe>(recipeView, HttpStatus.CREATED);
    }
	
	@PutMapping(value = "/recipes/updateRecipe/{id}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable("id") Long id ,@RequestBody RecipeImpl recipe) {
        LOGGER.info("Updating recipe name ");
        Recipe recipeView = restManager.updateRecipe(recipe);
        return new ResponseEntity<Recipe>(recipeView, HttpStatus.CREATED);
    }
    
    @DeleteMapping(value = "/recipes/deleteRecipe/{id}")
    public ResponseEntity<?> deleteRecipe(@PathVariable("id") Long id) {
    	LOGGER.info("Fetching & Deleting Recipe ");
        restManager.deleteRecipeById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @DeleteMapping(value = "/recipes/deleteInstruction/{id}")
    public ResponseEntity<?> deleteInstruction(@PathVariable("id") Long id) {
    	LOGGER.info("Fetching & Deleting Recipe instructions");
        restManager.deleteRecipeInstructionById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	
    @RequestMapping(value = "/recipes/addInstruction/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Recipe> addInstruction(@PathVariable("id") Long id ,@RequestBody RecipeImpl recipe) {
        LOGGER.info("Updating recipe name");
        Recipe recipeView = restManager.addInstruction(id, recipe);
        return new ResponseEntity<Recipe>(recipeView, HttpStatus.CREATED);
    }
    
}
