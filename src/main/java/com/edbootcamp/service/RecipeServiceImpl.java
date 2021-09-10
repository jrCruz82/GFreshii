package com.edbootcamp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edbootcamp.dao.RecipeDAO;
import com.edbootcamp.model.Recipe;

@Service("recipeService")
public class RecipeServiceImpl implements RecipeService{
	
	@Autowired
	private RecipeDAO dao;
	
	@Override
	public List<Recipe> fetchAllRecipes(){
		return dao.allRecipes();
	}

	@Override
	public Recipe saveRecipe(Recipe recipe) {
		return dao.saveRecipe(recipe);
	}

	@Override
	public boolean isRecipeExist(Recipe recipe) {
		
		return dao.isRecipeExist(recipe);
	}

	@Override
	public Recipe findById(long id) {
		return dao.findById(id);
	}

	@Override
	public Recipe updateRecipe(Recipe currentRecipe) {
		return dao.updateRecipe(currentRecipe);
	}

	@Override
	public void deleteRecipeById(Long id) {
		dao.deleteRecipeById(id);
		
	}
}
