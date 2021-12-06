package com.edbootcamp.serviceController;

import java.util.List;

import org.apache.log4j.LogManager;  
import org.apache.log4j.Logger; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edbootcamp.entity.IngredientImpl;
import com.edbootcamp.api.dao.IngredientDAO;
import com.edbootcamp.api.entity.Ingredient;

@Controller
@RequestMapping("user/recipes/")
public class IngredientController {

	private static Logger logger = LogManager.getLogger(IngredientController.class);

	@Autowired
	private IngredientDAO dao;
	
	@GetMapping(value = "/ingredientsByRecipe/{id}")
    public ResponseEntity<List<Ingredient>> getAllIngredientsByRecipeId(@PathVariable ("id") Long id) {
		logger.info("i am in the backend");
		List<Ingredient> ingredients = dao.allIngredients(id);
		return new ResponseEntity<List<Ingredient>>(ingredients, HttpStatus.OK);
    }
	
	@PostMapping(value = "/addIngredient/{id}")
    public ResponseEntity<List<Ingredient>> addIngredient(@PathVariable("id") Long id, @RequestBody IngredientImpl ingredient) {
		logger.info("Adding ingredient " + ingredient.getName());
 		dao.saveIngredient(id, ingredient);
        List<Ingredient> listIngredientView =  dao.allIngredients(id);
        return new ResponseEntity<List<Ingredient>>( listIngredientView, HttpStatus.OK);
    }
	
    @PutMapping(value = "/deleteIngredient/{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable("id") Long id, @RequestBody IngredientImpl ingredientView) {
    	Ingredient ingredient = dao.findByName(ingredientView.getName(),id);
        if (ingredient == null) {
        	logger.error("Unable to delete. Ingredient with name " + ingredientView.getName() + " not found");
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
        logger.info("deleting ingredient " + ingredient.getName() + " for recipe with id "+ id);
        dao.deleteIngredient(id, ingredient);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @PutMapping(value = "/updateIngredient/{id}")
    public ResponseEntity<List<Ingredient>> updateIngredient(@PathVariable("id") Long id, @RequestBody IngredientImpl ingredientView) {
    	Ingredient ingredient = dao.findById(ingredientView.getId());
        if (ingredient == null) {
        	logger.error("Unable to update. Ingredient with name " + ingredientView.getName() + " not found");
            return new ResponseEntity<List<Ingredient>>(HttpStatus.BAD_GATEWAY);
        }
        dao.updateIngredient(id, ingredientView);
        List<Ingredient> listIngredientView =  dao.allIngredients(id);
        return new ResponseEntity<List<Ingredient>>(listIngredientView, HttpStatus.NO_CONTENT);
    }
}
