package com.edbootcamp.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edbootcamp.api.manager.RecipeManager;
import com.edbootcamp.api.service.RecipeService;
import com.edbootcamp.entity.RecipeImpl;
import com.edbootcamp.view.RecipeViewImpl;

@Service("recipeManager")
public class RecipeManagerImpl implements RecipeManager{

	//Any code related to preparing view objects to be sent to UI, or coming back from the database
	
	@Autowired
	private RecipeService recipeService;
	
	public List<RecipeViewImpl> AllRecipes(){
		List<RecipeImpl> list = recipeService.fetchAllRecipes();
		List<RecipeViewImpl> viewList = new ArrayList<>();
		for(RecipeImpl recipeImpl: list) {
			RecipeViewImpl viewObjRecipeView = new RecipeViewImpl();
			BeanUtils.copyProperties(recipeImpl, viewObjRecipeView);
			viewList.add(viewObjRecipeView);
		}
		return viewList;
	}

	protected RecipeViewImpl convertToView(RecipeImpl recipe) {
		RecipeViewImpl view = new RecipeViewImpl();
		BeanUtils.copyProperties(recipe, view);
		return  view;
	}
	
	protected RecipeImpl convertToImpl(RecipeViewImpl recipe) {
		RecipeImpl impl = new RecipeImpl();
		BeanUtils.copyProperties(recipe, impl);
		return impl;
	}
	
	public RecipeViewImpl saveRecipe(RecipeViewImpl recipe) {
		RecipeImpl impl = convertToImpl(recipe);
		//load the impl with the view and then send to the service
		RecipeImpl savedIpmlImpl =  recipeService.saveRecipe(impl);
		RecipeViewImpl viewToReturn = convertToView(savedIpmlImpl);
		//create the view from the impl
		return viewToReturn;
	} 
	
	public boolean isRecipeExist(RecipeViewImpl recipe) {
		RecipeImpl impl = convertToImpl(recipe);
		return recipeService.isRecipeExist(impl);
	}
	
	public RecipeViewImpl findById(Long id) {
		RecipeImpl foundImpl = recipeService.findById(id);
		RecipeViewImpl viewRecipe = convertToView(foundImpl);
		return viewRecipe;
	}
	
	public RecipeViewImpl updateRecipe(RecipeViewImpl currentRecipe) {
		RecipeImpl impl = convertToImpl(currentRecipe);
		RecipeImpl updatedImpl = recipeService.updateRecipe(impl);
		RecipeViewImpl recipeView = convertToView(updatedImpl);
		return recipeView;
	}
	
	public void deleteRecipeById(Long id) {
		recipeService.deleteRecipeById(id);
	}

	public RecipeImpl getIngredientsById(RecipeImpl recipe) {
		return recipeService.getIngredientsById(recipe);
	}

	public RecipeViewImpl addInstruction(Long id, RecipeViewImpl recipe) {
		RecipeImpl impl = convertToImpl(recipe);
		RecipeImpl updatedImpl = recipeService.addInstruction(id,impl);
		RecipeViewImpl recipeView = convertToView(updatedImpl);
		return recipeView;
	}

	@Override
	public void deleteRecipeInstructionById(Long id) {
		recipeService.deleteRecipeInstructionById(id);
	}
	
}
