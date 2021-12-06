package com.edbootcamp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edbootcamp.api.dao.RecipeDAO;
import com.edbootcamp.api.entity.Recipe;
import com.edbootcamp.api.service.RecipeService;
import com.edbootcamp.entity.RecipeImpl;


@Service
public class RecipeServiceImpl implements RecipeService{
	
	@Autowired
	private RecipeDAO dao;
	@Transactional
	@Override
	public List<Recipe> fetchAllRecipes(Long id){
		return dao.allRecipes(id);
	}
	@Transactional
	@Override
	public Recipe saveRecipe(Recipe recipe, Long id) {
		return dao.saveRecipe(recipe, id);
	}
	@Transactional
	@Override
	public Recipe findById(Long id) {
		return dao.findById(id);
	}
	@Transactional
	@Override
	public Recipe updateRecipe(Recipe currentRecipe) {
		return dao.updateRecipe(currentRecipe);
	}
	@Transactional
	@Override
	public void deleteRecipeById(Long id) {
		dao.deleteRecipeById(id);
	}
	@Transactional
	@Override
	public Boolean isRecipeExist(Recipe recipe) {
		return dao.isRecipeExist(recipe);
	}
	@Transactional
	@Override
	public Recipe getIngredientsById(Recipe recipe) {
		return dao.getIngredientsById(recipe);
	}
	@Transactional
	@Override
	public Recipe addInstruction(Long id, Recipe impl) {
		return dao.addInstruction(id, impl);
	}
	@Transactional
	@Override
	public void deleteRecipeInstructionById(Long id) {
		dao.deleteRecipeInstructionById(id);
	}
	
}
