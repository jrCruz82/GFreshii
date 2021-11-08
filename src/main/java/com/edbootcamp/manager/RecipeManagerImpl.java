package com.edbootcamp.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edbootcamp.api.entity.Recipe;
import com.edbootcamp.api.entity.RecipeView;
import com.edbootcamp.api.manager.RecipeManager;
import com.edbootcamp.api.service.RecipeService;
import com.edbootcamp.entity.RecipeImpl;
import com.edbootcamp.view.RecipeViewImpl;

@Service("recipeManager")
public class RecipeManagerImpl implements RecipeManager{

	//Any code related to preparing view objects to be sent to UI, or coming back from the database
	
	@Autowired
	private RecipeService recipeService;
	
	public List<RecipeView> AllRecipes(){
		List<Recipe> list = recipeService.fetchAllRecipes();
		List<RecipeView> viewList = new ArrayList<>();
		for(Recipe recipeImpl: list) {
			RecipeView viewObjRecipeView = new RecipeViewImpl();
			BeanUtils.copyProperties(recipeImpl, viewObjRecipeView);
			viewList.add(viewObjRecipeView);
		}
		return viewList;
	}

	protected RecipeView convertToView(Recipe recipe) {
		RecipeView view = new RecipeViewImpl();
		BeanUtils.copyProperties(recipe, view);
		return  view;
	}
	
	protected Recipe convertToImpl(RecipeView recipe) {
		Recipe impl = new RecipeImpl();
		BeanUtils.copyProperties(recipe, impl);
		return impl;
	}
	
	public RecipeView saveRecipe(RecipeView recipe) {
		Recipe recipeImpl = convertToImpl(recipe);
		//load the impl with the view and then send to the service
		Recipe savedIpmlImpl =  recipeService.saveRecipe(recipeImpl);
		RecipeView viewToReturn = convertToView(savedIpmlImpl);
		//create the view from the impl
		return viewToReturn;
	} 
	
	public Boolean isRecipeExist(RecipeView recipe) {
		Recipe impl = convertToImpl(recipe);
		return recipeService.isRecipeExist(impl);
	}
	
	public RecipeView findById(Long id) {
		Recipe foundImpl = recipeService.findById(id);
		RecipeView viewRecipe = convertToView(foundImpl);
		return viewRecipe;
	}
	
	public RecipeView updateRecipe(RecipeView currentRecipe) {
		Recipe impl = convertToImpl(currentRecipe);
		Recipe updatedImpl = recipeService.updateRecipe(impl);
		RecipeView recipeView = convertToView(updatedImpl);
		return recipeView;
	}
	
	public void deleteRecipeById(Long id) {
		recipeService.deleteRecipeById(id);
	}

	public Recipe getIngredientsById(Recipe recipe) {
		return recipeService.getIngredientsById(recipe);
	}

	public RecipeView addInstruction(Long id, RecipeView recipe) {
		Recipe impl = convertToImpl(recipe);
		Recipe updatedImpl = recipeService.addInstruction(id,impl);
		RecipeView recipeView = convertToView(updatedImpl);
		return recipeView;
	}

	@Override
	public void deleteRecipeInstructionById(Long id) {
		recipeService.deleteRecipeInstructionById(id);
	}

	
}
