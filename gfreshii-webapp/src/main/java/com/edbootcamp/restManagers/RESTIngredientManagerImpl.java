package com.edbootcamp.restManagers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.edbootcamp.api.views.IngredientView;
import com.edbootcamp.view.IngredientViewImpl;
import com.edbootcamp.api.manager.IngredientManager;

@RestController
public class RESTIngredientManagerImpl implements IngredientManager{

	private final String REQUEST_URI = "http://localhost:8080/GFreshiiBackend/recipes";
	@Autowired
	private RestTemplate restTemplate;
	
	
	public List<IngredientView> allIngredientsByRecipe(Long id) {
		String requestUri = REQUEST_URI + "/ingredientsByRecipe/{id}";
		IngredientViewImpl[] response = restTemplate.getForObject(requestUri, IngredientViewImpl[].class, Long.toString(id));
		List<IngredientView> ingredients = new ArrayList<>();
		for(IngredientView ingredient: response) {
			ingredients.add(ingredient);
		}
		return ingredients;

	}
	
	public List<IngredientView> saveIngredient(Long id, IngredientView ingredient) {
		String requestUri = REQUEST_URI + "/addIngredient/{id}";
		Map < String, String > params = new HashMap < String, String > ();
        params.put("id", Long.toString(id));
        IngredientViewImpl[] response = restTemplate.postForObject(requestUri, ingredient, IngredientViewImpl[].class, params);
        List<IngredientView> ingredients = new ArrayList<>();
		for(IngredientView i: response) {
			ingredients.add(i);
		}
        return ingredients;
	}
	
	
	public void deleteIngredientById(Long id,IngredientView ingredient) {
		String requestUri = REQUEST_URI + "/deleteIngredient/";
		Map < String, String > params = new HashMap < String, String > ();
        params.put("id", Long.toString(id));
		restTemplate.put(requestUri + "/{id}", ingredient ,params);
	}

	
	public IngredientView updateIngredientById(Long id,IngredientView ingredient) {
		String requestUri = REQUEST_URI + "/updateIngredient/";
		Map < String, String > params = new HashMap < String, String > ();
        params.put("id", Long.toString(id));
		restTemplate.put(requestUri + "/{id}",ingredient, params);
		return ingredient;
	}
	

}
