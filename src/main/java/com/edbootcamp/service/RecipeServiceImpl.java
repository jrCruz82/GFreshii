package com.edbootcamp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edbootcamp.api.dao.RecipeDAO;
import com.edbootcamp.api.entity.Recipe;
import com.edbootcamp.api.service.RecipeService;


@Service
public class RecipeServiceImpl implements RecipeService{
	
	@Autowired
	private RecipeDAO dao;
	
	@Transactional
	@Override
	public List<Recipe> fetchAllRecipes(){
		return dao.allRecipes();
	}

	@Override
	public Recipe saveRecipe(Recipe recipe) {
		return dao.saveRecipe(recipe);
	}

	@Override
	public Recipe findById(Long id) {
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

	@Override
	public Boolean isRecipeExist(Recipe recipe) {
		return dao.isRecipeExist(recipe);
	}

	@Override
	public Recipe getIngredientsById(Recipe recipe) {
		return dao.getIngredientsById(recipe);
	}

	@Override
	public Recipe addInstruction(Long id, Recipe impl) {
		return dao.addInstruction(id, impl);
	}

	@Override
	public void deleteRecipeInstructionById(Long id) {
		dao.deleteRecipeInstructionById(id);
	}
	
}
