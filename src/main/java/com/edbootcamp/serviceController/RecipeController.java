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

import com.edbootcamp.api.dao.RecipeDAO;
import com.edbootcamp.api.entity.Recipe;
import com.edbootcamp.entity.RecipeImpl;




@RestController
public class RecipeController {

	private static Logger LOGGER = LoggerFactory.getLogger(RecipeController.class);

	@Autowired
	RecipeDAO dao;
	
	@GetMapping(value = "/recipes/")
    public ResponseEntity<List<?>> findAllRecipes() {
        List<Recipe> recipes =  dao.allRecipes();
        for(Recipe recipe: recipes) {
        	LOGGER.info("Recipes available so far: {}", recipe.getName());
        }
        
        if(recipes.isEmpty()){
        	LOGGER.error("No Recipes have been saved yet");
            return new ResponseEntity<List<?>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<?>>(recipes,HttpStatus.OK);
    }
	//add new recipe
	@PostMapping(value = "/recipes/createRecipe")
    public ResponseEntity<Recipe> createRecipe(@RequestBody RecipeImpl recipe) {
		LOGGER.info("The Recipe being created is {}",recipe);
		if (dao.isRecipeExist(recipe)) {
            LOGGER.error("A Recipe with name {} already exist",recipe.getName());
            return new ResponseEntity<Recipe>( HttpStatus.CONFLICT);
        }
		LOGGER.info("Creating Recipe " + recipe.getName());
		Recipe recipeView = dao.saveRecipe(recipe);
        return new ResponseEntity<Recipe>(recipeView, HttpStatus.CREATED);
    }
	
	@PutMapping(value = "/recipes/updateRecipe/{id}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable("id") Long id ,@RequestBody RecipeImpl recipe) {
		Recipe recipeV = dao.findById(id);
        if (recipeV == null) {
        	LOGGER.error("Unable to update. Recipe with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        LOGGER.info("Updating recipe name" + recipe.getName());
		Recipe recipeView = dao.updateRecipe(recipe);
        return new ResponseEntity<Recipe>(recipeView, HttpStatus.CREATED);
    }
    
    @DeleteMapping(value = "/recipes/deleteRecipe/{id}")
    public ResponseEntity<?> deleteRecipe(@PathVariable("id") Long id) {
    	LOGGER.info("Fetching & Deleting Recipe with id " + id);
        Recipe recipe = dao.findById(id);
        if (recipe == null) {
        	LOGGER.error("Unable to delete. Recipe with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        dao.deleteRecipeById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @DeleteMapping(value = "/recipes/deleteInstruction/{id}")
    public ResponseEntity<?> deleteInstruction(@PathVariable("id") Long id) {
    	LOGGER.info("Fetching & Deleting Recipe instructions with id " + id);
        Recipe recipe = dao.findById(id);
        if (recipe == null) {
        	LOGGER.error("Unable to delete Instructions. Recipe with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        dao.deleteRecipeInstructionById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	
    @RequestMapping(value = "/recipes/addInstruction/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Recipe> addInstruction(@PathVariable("id") Long id ,@RequestBody RecipeImpl recipe) {
    	Recipe recipeV = dao.findById(id);
    	LOGGER.info("Adding instructions to recipe " + recipe.getName());
        if (recipeV == null) {
        	LOGGER.error("Unable to add instruction. Recipe with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        LOGGER.info("Updating recipe name" + recipe.getName() + " with new instructions");
		Recipe recipeView = dao.addInstruction(id, recipe);
        return new ResponseEntity<Recipe>(recipeView, HttpStatus.CREATED);
    }
    
}
