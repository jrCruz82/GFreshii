package com.edbootcamp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edbootcamp.api.dao.RecipeDAO;
import com.edbootcamp.api.entity.Recipe;
import com.edbootcamp.api.service.RecipeService;
import com.edbootcamp.entity.RecipeImpl;


@Service("recipeService")
public class RecipeServiceImpl implements RecipeService{
	
	@Autowired
	private RecipeDAO dao;
	
	@Transactional
	@Override
	public List<RecipeImpl> fetchAllRecipes(){
		return dao.allRecipes();
	}

	@Override
	public RecipeImpl saveRecipe(RecipeImpl recipe) {
		return dao.saveRecipe(recipe);
	}

	@Override
	public RecipeImpl findById(Long id) {
		return dao.findById(id);
	}

	@Override
	public RecipeImpl updateRecipe(RecipeImpl currentRecipe) {
		return dao.updateRecipe(currentRecipe);
	}

	@Override
	public void deleteRecipeById(Long id) {
		dao.deleteRecipeById(id);
	}

	@Override
	public boolean isRecipeExist(RecipeImpl recipe) {
		return dao.isRecipeExist(recipe);
	}

	@Override
	public RecipeImpl getIngredientsById(RecipeImpl recipe) {
		return dao.getIngredientsById(recipe);
	}

	@Override
	public RecipeImpl addInstruction(Long id, RecipeImpl impl) {
		return dao.addInstruction(id, impl);
	}

	@Override
	public void deleteRecipeInstructionById(Long id) {
		dao.deleteRecipeInstructionById(id);
	}
	
}
