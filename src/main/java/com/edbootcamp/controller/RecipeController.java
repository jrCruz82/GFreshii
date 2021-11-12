package com.edbootcamp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.edbootcamp.api.entity.RecipeView;
import com.edbootcamp.api.manager.RecipeManager;
import com.edbootcamp.view.RecipeViewImpl;




@RestController
public class RecipeController {

	private static Logger LOGGER = LoggerFactory.getLogger(RecipeController.class);

	@Autowired
	RecipeManager recipeManager;
	
	@RequestMapping(value = "/recipes/", method = RequestMethod.GET)
    public ResponseEntity<List<?>> findAllRecipes() {
        List<RecipeView> recipes =  recipeManager.AllRecipes();
        for(RecipeView recipe: recipes) {
        	LOGGER.info("Recipes available so far: {}", recipe.getName());
        }
        
        if(recipes.isEmpty()){
            return new ResponseEntity<List<?>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<?>>(recipes,HttpStatus.OK);
    }
	//add new recipe
	@RequestMapping(value = "/recipes/createRecipe", method = RequestMethod.POST)
    public ResponseEntity<RecipeView> createRecipe(@RequestBody RecipeViewImpl recipe) {
		LOGGER.info("The Recipe being created is {}",recipe);
		if (recipeManager.isRecipeExist(recipe)) {
            LOGGER.error("A Recipe with name {} already exist",recipe.getName());
            return new ResponseEntity<RecipeView>( HttpStatus.CONFLICT);
        }
		System.out.println("Creating Recipe " + recipe.getName());
		RecipeView recipeView = recipeManager.saveRecipe(recipe);
        return new ResponseEntity<RecipeView>(recipeView, HttpStatus.CREATED);
    }
	
	@RequestMapping(value = "/recipes/updateRecipe/{id}", method = RequestMethod.PUT)
    public ResponseEntity<RecipeView> updateRecipe(@PathVariable("id") Long id ,@RequestBody RecipeViewImpl recipe) {
		RecipeView recipeV = recipeManager.findById(id);
        if (recipeV == null) {
            System.out.println("Unable to update. Recipe with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
		System.out.println("Updating recipe name" + recipe.getName());
		RecipeView recipeView = recipeManager.updateRecipe(recipe);
        return new ResponseEntity<RecipeView>(recipeView, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/recipes/deleteRecipe/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteRecipe(@PathVariable("id") Long id) {
        System.out.println("Fetching & Deleting Recipe with id " + id);
        RecipeView recipe = recipeManager.findById(id);
        if (recipe == null) {
            System.out.println("Unable to delete. Recipe with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        recipeManager.deleteRecipeById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(value = "/recipes/deleteInstruction/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteInstruction(@PathVariable("id") Long id) {
        System.out.println("Fetching & Deleting Recipe instructions with id " + id);
        RecipeView recipe = recipeManager.findById(id);
        if (recipe == null) {
            System.out.println("Unable to delete. Recipe with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        recipeManager.deleteRecipeInstructionById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	
    @RequestMapping(value = "/recipes/addInstruction/{id}", method = RequestMethod.PUT)
    public ResponseEntity<RecipeView> addInstruction(@PathVariable("id") Long id ,@RequestBody RecipeViewImpl recipe) {
    	RecipeView recipeV = recipeManager.findById(id);
        if (recipeV == null) {
            System.out.println("Unable to add instruction. Recipe with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
		System.out.println("Updating recipe name" + recipe.getName() + " with new instructions");
		RecipeView recipeView = recipeManager.addInstruction(id, recipe);
        return new ResponseEntity<RecipeView>(recipeView, HttpStatus.CREATED);
    }
    
}
