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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edbootcamp.api.entity.Recipe;
import com.edbootcamp.api.service.RecipeService;
import com.edbootcamp.entity.RecipeImpl;

@RestController
@RequestMapping("user/recipes/")
public class RecipeController {

	private static Logger logger = LogManager.getLogger(RecipeController.class);

	@Autowired
	private RecipeService recipeService;
	
	@GetMapping(value = "/{id}")
    public ResponseEntity<List<Recipe>> findAllRecipes(@PathVariable("id") Long id) {
		logger.info("Fetching list of recipes in the backend.");
        List<Recipe> recipes =  recipeService.fetchAllRecipes(id);
        return new ResponseEntity<List<Recipe>>(recipes,HttpStatus.OK);
    }
	//add new recipe
	@PostMapping(value = "/createRecipe/{id}")
    public ResponseEntity<Recipe> createRecipe(@PathVariable("id") Long id ,@RequestBody RecipeImpl recipe) {
		System.out.println(recipe+"\n\n");
		if (recipeService.isRecipeExist(recipe)) {
           logger.error("A Recipe with that name already exist");
            return new ResponseEntity<Recipe>( HttpStatus.CONFLICT);
        }
		logger.info("Creating Recipe " + recipe.getName());
		Recipe recipeView = recipeService.saveRecipe(recipe, id);
		logger.debug(recipeView);
        return new ResponseEntity<Recipe>(recipeView, HttpStatus.CREATED);
    }
	
	@PutMapping(value = "/updateRecipe/{id}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable("id") Long id ,@RequestBody RecipeImpl recipe) {
		Recipe recipeV = recipeService.findById(id);
        if (recipeV == null) {
        	logger.error("Unable to update. Recipe with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("Updating recipe with name " + recipe.getName());
		recipeService.updateRecipe(recipe);
		logger.info("updated recipe name " + recipe.getName() + " in the backend");
		
        return new ResponseEntity<Recipe>( HttpStatus.CREATED);
    }
    
    @DeleteMapping(value = "/deleteRecipe/{id}")
    public ResponseEntity<Recipe> deleteRecipe(@PathVariable("id") Long id) {
    	logger.info("Fetching & Deleting Recipe with id " + id);
        Recipe recipe = recipeService.findById(id);
        if (recipe == null) {
        	logger.error("Unable to delete. Recipe with id " + id + " not found");
            return new ResponseEntity<Recipe>(HttpStatus.NOT_FOUND);
        }
        recipeService.deleteRecipeById(id);
        return new ResponseEntity<Recipe>(HttpStatus.NO_CONTENT);
    }
    
    @DeleteMapping(value = "/deleteInstruction/{id}")
    public ResponseEntity<?> deleteInstruction(@PathVariable("id") Long id) {
    	logger.info("Fetching & Deleting Recipe instructions with id " + id);
        Recipe recipe = recipeService.findById(id);
        if (recipe == null) {
        	logger.error("Unable to delete Instructions. Recipe with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        recipeService.deleteRecipeInstructionById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	
    @PutMapping(value = "/addInstruction/{id}")
    public ResponseEntity<Recipe> addInstruction(@PathVariable("id") Long id ,@RequestBody RecipeImpl recipe) {
    	Recipe recipeV = recipeService.findById(id);
    	logger.info("Adding instructions to recipe " + recipe.getName());
        if (recipeV == null) {
        	logger.error("Unable to add instruction. Recipe with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("Updating recipe name " + recipe.getName() + " with new instructions");
		Recipe recipeView = recipeService.addInstruction(id, recipe);
        return new ResponseEntity<Recipe>( HttpStatus.CREATED);
    }
    
}
