package com.edbootcamp.restManagers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.edbootcamp.api.views.IngredientView;
import com.edbootcamp.view.IngredientViewImpl;
import com.edbootcamp.api.manager.IngredientManager;

@RestController
@Transactional
public class RESTIngredientManagerImpl implements IngredientManager{

	private final String REQUEST_URI = "http://localhost:8080/GFreshiiBackend/";
	@Autowired
	private RestTemplate restTemplate;
	
	
	public List<IngredientView> allIngredientsByRecipe(Long id) {
		String requestUri = REQUEST_URI + "recipes/ingredientsByRecipe/{id}";
		Map<String, String> params = new HashMap<>();
		params.put("id", Long.toString(id));
		ResponseEntity<IngredientViewImpl> responseEntity = restTemplate.getForEntity(requestUri, IngredientViewImpl.class, params);
		if(responseEntity.getBody() != null) {
			return Arrays.asList(responseEntity.getBody());
		}else {
			return Collections.emptyList();
		}

	}
	
	public IngredientView saveIngredient(Long id, IngredientView ingredient) {
		String requestUri = REQUEST_URI + "recipes/addIngredient/{id}";
		return restTemplate.postForObject(requestUri, ingredient, IngredientViewImpl.class);
	}
	
	
	public void deleteIngredientById(Long id,IngredientView ingredient) {
		String requestUri = REQUEST_URI + "recipes/deleteIngredient/{id}";
		restTemplate.put(requestUri + "/{id}", Long.toString(id), ingredient);
	}

	
	public IngredientView updateIngredientById(Long id,IngredientView ingredient) {
		String requestUri = REQUEST_URI + "recipes/updateIngredient/{id}";
		restTemplate.put(requestUri + "/{id}", Long.toString(id), ingredient);
		return ingredient;
	}

}
