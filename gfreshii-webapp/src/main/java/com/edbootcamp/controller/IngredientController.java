package com.edbootcamp.controller;

import java.util.List;

import org.apache.log4j.LogManager;  
import org.apache.log4j.Logger; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.edbootcamp.api.views.Ingredient;
import com.edbootcamp.restManagers.RESTIngredientManagerImpl;
import com.edbootcamp.view.IngredientImpl;

@RestController
public class IngredientController {

	private static Logger logger = LogManager.getLogger(IngredientController.class);
	
	@Autowired
	private RESTIngredientManagerImpl restManager ;
	
	@GetMapping(value = "/recipes/ingredientsByRecipe/{id}")
    public ResponseEntity<List<Ingredient>> getAllIngredientsByRecipeId(@PathVariable ("id") Long id) {  
		List<Ingredient> ingredients = restManager.allIngredientsByRecipe(id);
		return new ResponseEntity<List<Ingredient>>(ingredients, HttpStatus.OK);
    }
	
	@PostMapping(value = "/recipes/addIngredient/{id}")
    public ResponseEntity<List<Ingredient>> addIngredient(@PathVariable("id") Long id, @RequestBody IngredientImpl ingredient) {
		logger.info("Adding ingredient " + ingredient.getName());
		restManager.saveIngredient(id, ingredient);
        List<Ingredient> listIngredientView =  restManager.allIngredientsByRecipe(id);
        return new ResponseEntity<List<Ingredient>>( listIngredientView, HttpStatus.OK);
    }
	
    @PutMapping(value = "/recipes/deleteIngredient/{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable("id") Long id, @RequestBody IngredientImpl ingredientView) {
        restManager.deleteIngredientById(id, ingredientView);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @PutMapping(value = "/recipes/updateIngredient/{id}")
    public ResponseEntity<List<Ingredient>> updateIngredient(@PathVariable("id") Long id, @RequestBody IngredientImpl ingredientView) {
        restManager.updateIngredientById(id, ingredientView);
        List<Ingredient> listIngredient =  restManager.allIngredientsByRecipe(id);
        return new ResponseEntity<List<Ingredient>>(listIngredient, HttpStatus.NO_CONTENT);
    }
}
