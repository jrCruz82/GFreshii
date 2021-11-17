package com.edbootcamp.restManagers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.edbootcamp.api.manager.RecipeManager;
import com.edbootcamp.api.views.RecipeView;
import com.edbootcamp.view.RecipeViewImpl;

@Service("recipeManager")
public class RESTRecipeManagerImpl implements RecipeManager{

	//Any code related to preparing view objects to be sent to UI, or coming back from the database
	private final String REQUEST_URI = "http://localhost:8080/GFreshiiBackend/recipes";
	@Autowired
	private RestTemplate restTemplate;
	

	public List<RecipeView> allRecipes() {
		String requestUri = REQUEST_URI + "/";
		RecipeViewImpl[] response = restTemplate.getForObject(requestUri, RecipeViewImpl[].class);
		List<RecipeView> recipes = new ArrayList<>();
		for(RecipeView recipe: response) {
			recipes.add(recipe);
		}
		return recipes;
	}
	
	public RecipeView saveRecipe(RecipeView recipe) {
		String requestUri = REQUEST_URI + "/createRecipe";
		return restTemplate.postForObject(requestUri, recipe, RecipeViewImpl.class);
	}

	public RecipeView updateRecipe(RecipeView currentRecipe) {
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

	public RecipeView addInstruction(Long id, RecipeView recipe) {
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
