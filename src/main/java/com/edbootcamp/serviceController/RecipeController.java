package com.edbootcamp.serviceController;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.edbootcamp.api.entity.Recipe;
import com.edbootcamp.api.service.RecipeService;
import com.edbootcamp.entity.RecipeImpl;




@RestController
public class RecipeController {

	private static Logger LOGGER = LoggerFactory.getLogger(RecipeController.class);

	//private final String REQUEST_URI = "http://localhost:8080/GFreshii/";
	@Autowired
	private RecipeService recipeService;
	
	@GetMapping(value = "recipes/")
    public ResponseEntity<List<Recipe>> findAllRecipes() {
		LOGGER.info("Fetching list of recipes in the backend.");
        List<Recipe> recipes =  recipeService.fetchAllRecipes();
        for(Recipe recipe: recipes) {
        	LOGGER.info("Recipes available so far: {} from backend", recipe.getName());
        }
        
        if(recipes.isEmpty()){
        	LOGGER.error("No Recipes have been saved yet");
            return new ResponseEntity<List<Recipe>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Recipe>>(recipes,HttpStatus.OK);
    }
	//add new recipe
	@PostMapping(value = "recipes/createRecipe")
    public ResponseEntity<Recipe> createRecipe(@RequestBody RecipeImpl recipe) {
		LOGGER.info("The Recipe being created is {}",recipe);
		if (recipeService.isRecipeExist(recipe)) {
            LOGGER.error("A Recipe with name {} already exist",recipe.getName());
            return new ResponseEntity<Recipe>( HttpStatus.CONFLICT);
        }
		LOGGER.info("Creating Recipe " + recipe.getName());
		Recipe recipeView = recipeService.saveRecipe(recipe);
        return new ResponseEntity<Recipe>(recipeView, HttpStatus.CREATED);
    }
	
	@PutMapping(value = "recipes/updateRecipe/{id}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable("id") Long id ,@RequestBody RecipeImpl recipe) {
		Recipe recipeV = recipeService.findById(id);
        if (recipeV == null) {
        	LOGGER.error("Unable to update. Recipe with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        LOGGER.info("Updating recipe name " + recipe.getName());
		Recipe recipeView = recipeService.updateRecipe(recipe);
		LOGGER.info("updated recipe name " + recipe.getName() + " in the backend");
		
        return new ResponseEntity<Recipe>( HttpStatus.CREATED);
    }
    
    @DeleteMapping(value = "recipes/deleteRecipe/{id}")
    public ResponseEntity<Recipe> deleteRecipe(@PathVariable("id") Long id) {
    	LOGGER.info("Fetching & Deleting Recipe with id " + id);
        Recipe recipe = recipeService.findById(id);
        if (recipe == null) {
        	LOGGER.error("Unable to delete. Recipe with id " + id + " not found");
            return new ResponseEntity<Recipe>(HttpStatus.NOT_FOUND);
        }
        recipeService.deleteRecipeById(id);
        return new ResponseEntity<Recipe>(HttpStatus.NO_CONTENT);
    }
    
    @DeleteMapping(value = "recipes/deleteInstruction/{id}")
    public ResponseEntity<?> deleteInstruction(@PathVariable("id") Long id) {
    	LOGGER.info("Fetching & Deleting Recipe instructions with id " + id);
        Recipe recipe = recipeService.findById(id);
        if (recipe == null) {
        	LOGGER.error("Unable to delete Instructions. Recipe with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        recipeService.deleteRecipeInstructionById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	
    @PutMapping(value = "recipes/addInstruction/{id}")
    public ResponseEntity<Recipe> addInstruction(@PathVariable("id") Long id ,@RequestBody RecipeImpl recipe) {
    	Recipe recipeV = recipeService.findById(id);
    	LOGGER.info("Adding instructions to recipe " + recipe.getName());
        if (recipeV == null) {
        	LOGGER.error("Unable to add instruction. Recipe with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        LOGGER.info("Updating recipe name " + recipe.getName() + " with new instructions");
		Recipe recipeView = recipeService.addInstruction(id, recipe);
        return new ResponseEntity<Recipe>( HttpStatus.CREATED);
    }
    
}
