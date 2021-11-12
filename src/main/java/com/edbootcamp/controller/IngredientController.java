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

import com.edbootcamp.api.entity.IngredientView;
import com.edbootcamp.api.manager.IngredientManager;
import com.edbootcamp.view.IngredientViewImpl;

@RestController
public class IngredientController {

	private static Logger LOGGER = LoggerFactory.getLogger("IngredientController");
	@Autowired
	private IngredientManager ingredientManager;
	
	@RequestMapping(value = "/recipes/ingredientsByRecipe/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<IngredientView>> getAllIngredientsByRecipeId(@PathVariable ("id") Long id) {
        
		List<IngredientView> ingredients = ingredientManager.allIngredientsByRecipe(id);
        
		for(IngredientView list: ingredients) {
        	LOGGER.info("Recipe ingredients for recipe Id:{} ",list.getName() );
        }
		return new ResponseEntity<List<IngredientView>>(ingredients, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/recipes/addIngredient/{id}", method = RequestMethod.POST)
    public ResponseEntity<List<IngredientView>> addIngredient(@PathVariable("id") Long id, @RequestBody IngredientViewImpl ingredient) {
		System.out.println("Adding ingredient " + ingredient.getName());
 		ingredientManager.saveIngredient(id,ingredient);
        List<IngredientView> listIngredientView =  ingredientManager.allIngredientsByRecipe(id);
        System.out.println(listIngredientView);
        return new ResponseEntity<List<IngredientView>>( listIngredientView, HttpStatus.OK);
    }
	
    @RequestMapping(value = "/recipes/deleteIngredient/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> deleteIngredient(@PathVariable("id") Long id, @RequestBody IngredientViewImpl ingredientView) {
    	IngredientView ingredient = ingredientManager.findByName(ingredientView.getName(),id);
        if (ingredient == null) {
            System.out.println("Unable to delete. Ingredient with name " + ingredientView.getName() + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ingredientManager.deleteIngredientById(id, ingredient);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(value = "/recipes/updateIngredient/{id}", method = RequestMethod.PUT)
    public ResponseEntity<List<IngredientView>> updateIngredient(@PathVariable("id") Long id, @RequestBody IngredientViewImpl ingredientView) {
    	IngredientView ingredient = ingredientManager.findById(ingredientView.getId());
        if (ingredient == null) {
            System.out.println("Unable to update. Ingredient with name " + ingredientView.getName() + " not found");
            return new ResponseEntity<List<IngredientView>>(HttpStatus.NOT_FOUND);
        }
        ingredientManager.updateIngredientById(id, ingredientView);
        List<IngredientView> listIngredientView =  ingredientManager.allIngredientsByRecipe(id);
        return new ResponseEntity<List<IngredientView>>(listIngredientView, HttpStatus.NO_CONTENT);
    }
}
