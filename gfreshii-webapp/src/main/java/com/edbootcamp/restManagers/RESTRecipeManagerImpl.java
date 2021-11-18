package com.edbootcamp.restManagers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.edbootcamp.api.manager.RecipeManager;
import com.edbootcamp.api.views.Recipe;
import com.edbootcamp.view.RecipeImpl;

@Service
public class RESTRecipeManagerImpl implements RecipeManager{

	//Any code related to preparing view objects to be sent to UI, or coming back from the database
	private final String REQUEST_URI = "http://localhost:8080/GFreshiiBackend/recipes";
	@Autowired
	private RestTemplate restTemplate;
	

	public List<Recipe> allRecipes() {
		String requestUri = REQUEST_URI + "/";
		RecipeImpl[] response = restTemplate.getForObject(requestUri, RecipeImpl[].class);
		List<Recipe> recipes = new ArrayList<>();
		for(Recipe recipe: response) {
			recipes.add(recipe);
		}
		return recipes;
	}
	
	public Recipe saveRecipe(Recipe recipe) {
		String requestUri = REQUEST_URI + "/createRecipe";
		return restTemplate.postForObject(requestUri, recipe, RecipeImpl.class);
	}

	public Recipe updateRecipe(Recipe currentRecipe) {
		String requestUri = REQUEST_URI + "/updateRecipe/";
		Long id = currentRecipe.getId();
		Map < String, String > params = new HashMap < String, String > ();
        params.put("id", Long.toString(id));
        restTemplate.put(requestUri + "/{id}", currentRecipe, params);
		return currentRecipe;
	}
	
	public void deleteRecipeById(Long id) {
		String requestUri = REQUEST_URI + "/deleteRecipe/";
		Map < String, String > params = new HashMap < String, String > ();
        params.put("id", Long.toString(id));
		restTemplate.delete(requestUri + "/{id}",params);
	}

	public Recipe addInstruction(Long id, Recipe recipe) {
		String requestUri = REQUEST_URI + "/addInstruction/";
		Map < String, String > params = new HashMap < String, String > ();
        params.put("id", Long.toString(id));
		restTemplate.put(requestUri + "/{id}", recipe, params);
		return recipe;
	}


	public void deleteRecipeInstructionById(Long id) {
		String requestUri = REQUEST_URI + "/deleteInstruction/";
		restTemplate.delete(requestUri + "/{id}", Long.toString(id));
	}


}
