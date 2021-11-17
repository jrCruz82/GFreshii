package com.edbootcamp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.edbootcamp.api.views.RecipeView;
import com.edbootcamp.restManagers.RESTRecipeManagerImpl;
import com.edbootcamp.view.RecipeViewImpl;




@Controller
public class RecipeController {

	private static Logger LOGGER = LoggerFactory.getLogger(RecipeController.class);

	@Autowired
	private RESTRecipeManagerImpl restManager;
	
	@GetMapping(value = "recipes/",headers = "Accept=application/json" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RecipeView>> findAllRecipes() {
		LOGGER.info("Looking for recipes in restmanager");
        List<RecipeView> recipes =  restManager.allRecipes();
        System.out.println(recipes);
        LOGGER.info("Looking for recipes in restmanager after returning from backend");
        for(RecipeView recipe: recipes) {
        	LOGGER.info("Recipes available so for the frontend: {}", recipe.getName());
        }
        
        if(recipes.isEmpty()){
        	LOGGER.error("No Recipes have been saved yet");
            return new ResponseEntity<List<RecipeView>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<RecipeView>>(recipes, HttpStatus.OK);
    }
	//add new recipe
	@PostMapping(value = "/recipes/createRecipe")
    public ResponseEntity<RecipeView> createRecipe(@RequestBody RecipeViewImpl recipe) {
		LOGGER.info("The Recipe being created is {}",recipe);
//		if (restManager.isRecipeExist(recipe)) {
//            LOGGER.error("A Recipe with name {} already exist",recipe.getName());
//            return new ResponseEntity<RecipeView>( HttpStatus.CONFLICT);
//        }
		LOGGER.info("Creating Recipe " + recipe.getName());
		RecipeView recipeView = restManager.saveRecipe(recipe);
        return new ResponseEntity<RecipeView>(recipeView, HttpStatus.CREATED);
    }
	
	@PutMapping(value = "/recipes/updateRecipe/{id}")
    public ResponseEntity<RecipeView> updateRecipe(@PathVariable("id") Long id ,@RequestBody RecipeViewImpl recipe) {
//		RecipeView recipeV = restManager.findById(id);
//        if (recipeV == null) {
//        	LOGGER.error("Unable to update. Recipe with id " + id + " not found");
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
        LOGGER.info("Updating recipe name " + recipe.getName());
        RecipeView recipeView = restManager.updateRecipe(recipe);
        return new ResponseEntity<RecipeView>(recipeView, HttpStatus.CREATED);
    }
    
    @DeleteMapping(value = "/recipes/deleteRecipe/{id}")
    public ResponseEntity<?> deleteRecipe(@PathVariable("id") Long id) {
    	LOGGER.info("Fetching & Deleting Recipe with id " + id);
//    	RecipeView recipe = restManager.findById(id);
//        if (recipe == null) {
//        	LOGGER.error("Unable to delete. Recipe with id " + id + " not found");
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
        restManager.deleteRecipeById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @DeleteMapping(value = "/recipes/deleteInstruction/{id}")
    public ResponseEntity<?> deleteInstruction(@PathVariable("id") Long id) {
    	LOGGER.info("Fetching & Deleting Recipe instructions with id " + id);
//    	RecipeView recipe = restManager.findById(id);
//        if (recipe == null) {
//        	LOGGER.error("Unable to delete Instructions. Recipe with id " + id + " not found");
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
        restManager.deleteRecipeInstructionById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	
    @RequestMapping(value = "/recipes/addInstruction/{id}", method = RequestMethod.PUT)
    public ResponseEntity<RecipeView> addInstruction(@PathVariable("id") Long id ,@RequestBody RecipeViewImpl recipe) {
//    	RecipeView recipeV = restManager.findById(id);
//    	LOGGER.info("Adding instructions to recipe " + recipe.getName());
//        if (recipeV == null) {
//        	LOGGER.error("Unable to add instruction. Recipe with id " + id + " not found");
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
        LOGGER.info("Updating recipe name " + recipe.getName() + " with new instructions frontend");
        RecipeView recipeView = restManager.addInstruction(id, recipe);
        return new ResponseEntity<RecipeView>(recipeView, HttpStatus.CREATED);
    }
    
}
