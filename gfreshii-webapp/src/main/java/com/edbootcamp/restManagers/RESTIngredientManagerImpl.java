package com.edbootcamp.restManagers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.edbootcamp.api.views.Ingredient;
import com.edbootcamp.view.IngredientImpl;
import com.edbootcamp.api.manager.IngredientManager;

@RestController
public class RESTIngredientManagerImpl implements IngredientManager{

	private final String REQUEST_URI = "http://localhost:8080/GFreshiiBackend/user/recipes";
	@Autowired
	private RestTemplate restTemplate;
	
	
	public List<Ingredient> allIngredientsByRecipe(Long id) {
		String requestUri = REQUEST_URI + "/ingredientsByRecipe/{id}";
		IngredientImpl[] response = restTemplate.getForObject(requestUri, IngredientImpl[].class, Long.toString(id));
		List<Ingredient> ingredients = new ArrayList<>();
		for(Ingredient ingredient: response) {
			ingredients.add(ingredient);
		}
		return ingredients;

	}
	
	public List<Ingredient> saveIngredient(Long id, Ingredient ingredient) {
		String requestUri = REQUEST_URI + "/addIngredient/{id}";
		Map < String, String > params = new HashMap < String, String > ();
        params.put("id", Long.toString(id));
        IngredientImpl[] response = restTemplate.postForObject(requestUri, ingredient, IngredientImpl[].class, params);
        List<Ingredient> ingredients = new ArrayList<>();
		for(Ingredient i: response) {
			ingredients.add(i);
		}
        return ingredients;
	}
	
	
	public void deleteIngredientById(Long id,Ingredient ingredient) {
		String requestUri = REQUEST_URI + "/deleteIngredient/";
		Map < String, String > params = new HashMap < String, String > ();
        params.put("id", Long.toString(id));
		restTemplate.put(requestUri + "/{id}", ingredient ,params);
	}

	
	public Ingredient updateIngredientById(Long id,Ingredient ingredient) {
		String requestUri = REQUEST_URI + "/updateIngredient/";
		Map < String, String > params = new HashMap < String, String > ();
        params.put("id", Long.toString(id));
		restTemplate.put(requestUri + "/{id}",ingredient, params);
		return ingredient;
	}
	

}
