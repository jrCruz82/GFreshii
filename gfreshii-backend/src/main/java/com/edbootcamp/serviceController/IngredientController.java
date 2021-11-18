package com.edbootcamp.serviceController;

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

import com.edbootcamp.entity.IngredientImpl;
import com.edbootcamp.api.dao.IngredientDAO;
import com.edbootcamp.api.entity.Ingredient;

@RestController
public class IngredientController {

	private static Logger logger = LogManager.getLogger(IngredientController.class);

	@Autowired
	IngredientDAO iDao;
	
	@GetMapping(value = "/recipes/ingredientsByRecipe/{id}")
    public ResponseEntity<List<Ingredient>> getAllIngredientsByRecipeId(@PathVariable ("id") Long id) {
		logger.info("i am in the backend");
		List<Ingredient> ingredients = iDao.allIngredients(id);
		return new ResponseEntity<List<Ingredient>>(ingredients, HttpStatus.OK);
    }
	
	@PostMapping(value = "/recipes/addIngredient/{id}")
    public ResponseEntity<List<Ingredient>> addIngredient(@PathVariable("id") Long id, @RequestBody IngredientImpl ingredient) {
		logger.info("Adding ingredient " + ingredient.getName());
 		iDao.saveIngredient(id, ingredient);
        List<Ingredient> listIngredientView =  iDao.allIngredients(id);
        return new ResponseEntity<List<Ingredient>>( listIngredientView, HttpStatus.OK);
    }
	
    @PutMapping(value = "/recipes/deleteIngredient/{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable("id") Long id, @RequestBody IngredientImpl ingredientView) {
    	Ingredient ingredient = iDao.findByName(ingredientView.getName(),id);
        if (ingredient == null) {
        	logger.error("Unable to delete. Ingredient with name " + ingredientView.getName() + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("deleting ingredient " + ingredient.getName() + " for recipe with id "+ id);
        iDao.deleteIngredient(id, ingredient);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @PutMapping(value = "/recipes/updateIngredient/{id}")
    public ResponseEntity<List<Ingredient>> updateIngredient(@PathVariable("id") Long id, @RequestBody IngredientImpl ingredientView) {
    	Ingredient ingredient = iDao.findById(ingredientView.getId());
        if (ingredient == null) {
        	logger.error("Unable to update. Ingredient with name " + ingredientView.getName() + " not found");
            return new ResponseEntity<List<Ingredient>>(HttpStatus.NOT_FOUND);
        }
        iDao.updateIngredient(id, ingredientView);
        List<Ingredient> listIngredientView =  iDao.allIngredients(id);
        return new ResponseEntity<List<Ingredient>>(listIngredientView, HttpStatus.NO_CONTENT);
    }
}
