package com.edbootcamp.restManagers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.edbootcamp.api.manager.RecipeManager;
import com.edbootcamp.api.views.RecipeView;
import com.edbootcamp.view.RecipeViewImpl;

@Service("recipeManager")
public class RESTRecipeManagerImpl implements RecipeManager{

	//Any code related to preparing view objects to be sent to UI, or coming back from the database
	private final String REQUEST_URI = "http://localhost:8080/GFreshiiBackend/";
	@Autowired
	private RestTemplate restTemplate;
	
	public List<RecipeView> allRecipes() {
		String requestUri = REQUEST_URI + "/recipes/";
		@SuppressWarnings("unchecked")
		List<RecipeView> recipes = (List<RecipeView>) restTemplate.getForObject(requestUri, RecipeViewImpl.class);
		return recipes;
	}
	
	public RecipeView saveRecipe(RecipeView recipe) {
		String requestUri = REQUEST_URI + "recipes/createRecipe";
		return restTemplate.postForObject(requestUri, recipe, RecipeViewImpl.class);
	}
	

	public RecipeView updateRecipe(RecipeView currentRecipe) {
		String requestUri = REQUEST_URI + "recipes/updateRecipe/{id}";
		Long id = currentRecipe.getId();
		restTemplate.put(requestUri + "/{id}", Long.toString(id), currentRecipe);
		return currentRecipe;
	}
	
	public void deleteRecipeById(Long id) {
		String requestUri = REQUEST_URI + "recipes/deleteRecipe/{id}";
		restTemplate.delete(requestUri + "/{id}", Long.toString(id));
	}

	public RecipeView addInstruction(Long id, RecipeView recipe) {
		String requestUri = REQUEST_URI + "recipes/addInstruction/{id}";
		restTemplate.put(requestUri + "/{id}",Long.toString(id), recipe);
		return recipe;
	}


	public void deleteRecipeInstructionById(Long id) {
		String requestUri = REQUEST_URI + "recipes/deleteInstruction/{id}";
		restTemplate.delete(requestUri + "/{id}", Long.toString(id));
	}


}
