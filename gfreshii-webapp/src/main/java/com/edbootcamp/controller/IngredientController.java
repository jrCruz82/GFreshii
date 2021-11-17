package com.edbootcamp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.edbootcamp.api.views.IngredientView;
import com.edbootcamp.restManagers.RESTIngredientManagerImpl;
import com.edbootcamp.view.IngredientViewImpl;

@RestController
public class IngredientController {

	private static Logger LOGGER = LoggerFactory.getLogger(IngredientController.class);
	
	@Autowired
	private RESTIngredientManagerImpl restManager ;
	
	@GetMapping(value = "/recipes/ingredientsByRecipe/{id}")
    public ResponseEntity<List<IngredientView>> getAllIngredientsByRecipeId(@PathVariable ("id") Long id) {
        
		List<IngredientView> ingredients = restManager.allIngredientsByRecipe(id);
        
		for(IngredientView list: ingredients) {
        	LOGGER.info("Recipe ingredients for recipe Id:{} ",list.getName() );
        }
		return new ResponseEntity<List<IngredientView>>(ingredients, HttpStatus.OK);
    }
	
	@PostMapping(value = "/recipes/addIngredient/{id}")
    public ResponseEntity<List<IngredientView>> addIngredient(@PathVariable("id") Long id, @RequestBody IngredientViewImpl ingredient) {
		LOGGER.info("Adding ingredient " + ingredient.getName());
		restManager.saveIngredient(id, ingredient);
        List<IngredientView> listIngredientView =  restManager.allIngredientsByRecipe(id);
        for(IngredientView list: listIngredientView) {
        	LOGGER.info("Recipe ingredients for recipe Id:{} ",list.getName() );
        }
        return new ResponseEntity<List<IngredientView>>( listIngredientView, HttpStatus.OK);
    }
	
    @PutMapping(value = "/recipes/deleteIngredient/{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable("id") Long id, @RequestBody IngredientViewImpl ingredientView) {
        restManager.deleteIngredientById(id, ingredientView);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @PutMapping(value = "/recipes/updateIngredient/{id}")
    public ResponseEntity<List<IngredientView>> updateIngredient(@PathVariable("id") Long id, @RequestBody IngredientViewImpl ingredientView) {
        restManager.updateIngredientById(id, ingredientView);
        List<IngredientView> listIngredientView =  restManager.allIngredientsByRecipe(id);
        return new ResponseEntity<List<IngredientView>>(listIngredientView, HttpStatus.NO_CONTENT);
    }
}
