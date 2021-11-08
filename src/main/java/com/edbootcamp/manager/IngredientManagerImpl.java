package com.edbootcamp.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edbootcamp.api.entity.Ingredient;
import com.edbootcamp.api.entity.IngredientView;
import com.edbootcamp.api.entity.Recipe;
import com.edbootcamp.api.manager.IngredientManager;
import com.edbootcamp.api.service.IngredientService;
import com.edbootcamp.entity.IngredientImpl;
import com.edbootcamp.view.RecipeViewImpl;
import com.edbootcamp.view.IngredientViewImpl;

@Service("ingredientManager")
public class IngredientManagerImpl implements IngredientManager{

	@Autowired
	private IngredientService ingredientService;
	@Autowired
	private RecipeManagerImpl recipeManager;
	
	private IngredientView convertToView(Ingredient ingredient) {
		IngredientView viewimpl = new IngredientViewImpl();
		BeanUtils.copyProperties(ingredient, viewimpl);
		return  viewimpl;
	}
	
	private Ingredient convertToImpl(IngredientView ingredient) {
		IngredientImpl impl = new IngredientImpl();
		BeanUtils.copyProperties(ingredient,impl);
		return  impl;
	}
	
	public Recipe getRecipe(RecipeViewImpl recipeView) {
		Recipe impl = recipeManager.convertToImpl(recipeView);
		return impl;
	}
	
	public List<IngredientView> allIngredientsByRecipe(Long id) {
		List<Ingredient> list = ingredientService.fetchAllIngredientsByRecipe(id);
		List<IngredientView> viewList = new ArrayList<IngredientView>();
		for(Ingredient ingredientImpl: list) {
			IngredientView viewObjRecipeView = new IngredientViewImpl();
			BeanUtils.copyProperties(ingredientImpl, viewObjRecipeView);
			viewList.add(viewObjRecipeView);
		}
		return viewList;
	}

	
	public IngredientView saveIngredient(Long id, IngredientView ingredient) {
		Ingredient impl = new IngredientImpl();
		BeanUtils.copyProperties(ingredient, impl);
		Ingredient savedIngredient =  ingredientService.saveIngredient(id,impl);
		IngredientView viewIngredient = new IngredientViewImpl();
		viewIngredient = convertToView(savedIngredient);
		return viewIngredient;
	}

	@Override
	public IngredientView findByName(String name, Long id) {
		Ingredient ingredient = ingredientService.findByName(name, id);
		IngredientView ingredientView = new IngredientViewImpl();
		BeanUtils.copyProperties(ingredient, ingredientView);
		return ingredientView;
	}

	@Override
	public void deleteIngredientById(Long id,IngredientView ingredient) {
		Ingredient impl = new IngredientImpl();
		BeanUtils.copyProperties(ingredient, impl);
		ingredientService.deleteIngredient(id, impl);
	}

	@Override
	public IngredientView updateIngredientById(Long id, IngredientView ingredient) {
		Ingredient impl = convertToImpl(ingredient);
		impl = ingredientService.updateIngredient(id, impl);
		ingredient = convertToView(impl);
		return ingredient;
	}

	@Override
	public IngredientView findById(Long id) {
		Ingredient impl = new IngredientImpl();
		impl = ingredientService.findById(id);
		return convertToView(impl);
	}

}
