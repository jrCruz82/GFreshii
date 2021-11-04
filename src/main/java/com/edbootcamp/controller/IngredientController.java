package com.edbootcamp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.edbootcamp.api.manager.IngredientManager;
import com.edbootcamp.view.IngredientViewImpl;

@RestController
public class IngredientController {

	@Autowired
	private IngredientManager ingredientManager;
	
	@RequestMapping(value = "/recipes/ingredientsByRecipe/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<IngredientViewImpl>> getAllIngredientsByRecipeId(@PathVariable ("id") Long id) {
        List<IngredientViewImpl> ingredients = ingredientManager.allIngredientsByRecipe(id);
        for(IngredientViewImpl list: ingredients) {
        	System.out.println(list.getName());
        }
		return new ResponseEntity<List<IngredientViewImpl>>(ingredients, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/recipes/addIngredient/{id}", method = RequestMethod.POST)
    public ResponseEntity<List<IngredientViewImpl>> addIngredient(@PathVariable("id") Long id, @RequestBody IngredientViewImpl ingredient) {
		System.out.println("Adding ingredient " + ingredient.getName());
 		ingredientManager.saveIngredient(id,ingredient);
        List<IngredientViewImpl> listIngredientView =  ingredientManager.allIngredientsByRecipe(id);
        System.out.println(listIngredientView);
        return new ResponseEntity<List<IngredientViewImpl>>( listIngredientView, HttpStatus.OK);
    }
	
    @RequestMapping(value = "/recipes/deleteIngredient/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> deleteIngredient(@PathVariable("id") Long id, @RequestBody IngredientViewImpl ingredientView) {
        IngredientViewImpl ingredient = ingredientManager.findByName(ingredientView.getName(),id);
        if (ingredient == null) {
            System.out.println("Unable to delete. Ingredient with name " + ingredientView.getName() + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ingredientManager.deleteIngredientById(id, ingredient);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(value = "/recipes/updateIngredient/{id}", method = RequestMethod.PUT)
    public ResponseEntity<List<IngredientViewImpl>> updateIngredient(@PathVariable("id") Long id, @RequestBody IngredientViewImpl ingredientView) {
    	IngredientViewImpl ingredient = ingredientManager.findById(ingredientView.getId());
        if (ingredient == null) {
            System.out.println("Unable to update. Ingredient with name " + ingredientView.getName() + " not found");
            return new ResponseEntity<List<IngredientViewImpl>>(HttpStatus.NOT_FOUND);
        }
        IngredientViewImpl updatedViewImpl = ingredientManager.updateIngredientById(id, ingredientView);
        List<IngredientViewImpl> listIngredientView =  ingredientManager.allIngredientsByRecipe(id);
        return new ResponseEntity<List<IngredientViewImpl>>(listIngredientView, HttpStatus.NO_CONTENT);
    }
}
