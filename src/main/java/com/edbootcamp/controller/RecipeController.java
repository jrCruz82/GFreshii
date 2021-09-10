package com.edbootcamp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.edbootcamp.model.Recipe;
import com.edbootcamp.service.RecipeService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
public class RecipeController {

	@Autowired
	RecipeService recipeService;
	//list all recipes
	@RequestMapping(value = "/recipes/", method = RequestMethod.GET)
    public ResponseEntity<List<Recipe>> findAllRecipes() {
    	
        List<Recipe> recipes = recipeService.fetchAllRecipes();
        if(recipes.isEmpty()){
            return new ResponseEntity<List<Recipe>>(HttpStatus.NO_CONTENT);
        }
        
        return new ResponseEntity<List<Recipe>>(recipes, HttpStatus.OK);
    }
	//add new recipe
	@RequestMapping(value = "/recipes/createRecipe", method = RequestMethod.POST)
    public ResponseEntity<List<Recipe>> createRecipe(@Valid Recipe recipe, BindingResult result ,UriComponentsBuilder ucBuilder) {
        if(result.hasErrors()) {
        	return new ResponseEntity<List<Recipe>>(HttpStatus.BAD_REQUEST);
        }
		System.out.println("Creating Recipe " + recipe.getName());
  
        if (recipeService.isRecipeExist(recipe)) {
            System.out.println("A Recipe with name " + recipe.getName() + " already exist");
            return new ResponseEntity<List<Recipe>>(HttpStatus.CONFLICT);
        }
  
        recipeService.saveRecipe(recipe);
  
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/recipes/{id}").buildAndExpand(recipe.getId()).toUri());
        return new ResponseEntity<List<Recipe>>(headers, HttpStatus.CREATED);
    }
	
    @RequestMapping(value = "/recipes/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Recipe> getRecipe(@PathVariable("id") long id) {
        System.out.println("Fetching Recipe with id " + id);
        Recipe recipe = recipeService.findById(id);
        if (recipe == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<Recipe>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Recipe>(recipe, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/recipes/updateRecipe/{id}", method = RequestMethod.PUT)
    
    public ResponseEntity<Recipe> updateUser(@PathVariable("id") long id, @RequestBody Recipe recipe) {
    	ObjectMapper mapper = new ObjectMapper();
    	mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    	System.out.println("Updating Recipe " + recipe.getName());
        
        Recipe currentRecipe = recipeService.findById(id);
        
        if (currentRecipe==null) {
            System.out.println("Recipe with id " + id + " not found");
            return new ResponseEntity<Recipe>(HttpStatus.NOT_FOUND);
        }
        
        currentRecipe.setName(recipe.getName());
        currentRecipe.setIngredients(recipe.getIngredients());
        currentRecipe.setInstruction(recipe.getInstruction());
          
        recipeService.updateRecipe(currentRecipe);
        
        return new ResponseEntity<Recipe>(currentRecipe, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/recipes/deleteRecipe/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteRecipe(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Recipe with id " + id);
  
        Recipe recipe = recipeService.findById(id);
        if (recipe == null) {
            System.out.println("Unable to delete. Recipe with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        recipeService.deleteRecipeById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
