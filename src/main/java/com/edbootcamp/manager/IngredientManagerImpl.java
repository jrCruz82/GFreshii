package com.edbootcamp.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edbootcamp.api.entity.Ingredient;
import com.edbootcamp.api.entity.RecipeView;
import com.edbootcamp.api.manager.IngredientManager;
import com.edbootcamp.api.service.IngredientService;
import com.edbootcamp.entity.IngredientImpl;
import com.edbootcamp.entity.RecipeImpl;
import com.edbootcamp.view.RecipeViewImpl;
import com.edbootcamp.view.IngredientViewImpl;

@Service("ingredientManager")
public class IngredientManagerImpl implements IngredientManager{

	@Autowired
	private IngredientService ingredientService;
	@Autowired
	private RecipeManagerImpl recipeManager;
	
	private IngredientViewImpl convertToView(IngredientImpl ingredient) {
		IngredientViewImpl viewimpl = new IngredientViewImpl();
		BeanUtils.copyProperties(ingredient, viewimpl);
		return  viewimpl;
	}
	
	private IngredientImpl convertToImpl(IngredientViewImpl ingredient) {
		IngredientImpl impl = new IngredientImpl();
		BeanUtils.copyProperties(ingredient,impl);
		return  impl;
	}
	
	public RecipeImpl getRecipe(RecipeViewImpl recipeView) {
		RecipeImpl impl = recipeManager.convertToImpl(recipeView);
		return impl;
	}
	
	public List<IngredientViewImpl> allIngredientsByRecipe(Long id) {
		List<IngredientImpl> list = ingredientService.fetchAllIngredientsByRecipe(id);
		List<IngredientViewImpl> viewList = new ArrayList<IngredientViewImpl>();
		for(IngredientImpl ingredientImpl: list) {
			IngredientViewImpl viewObjRecipeView = new IngredientViewImpl();
			BeanUtils.copyProperties(ingredientImpl, viewObjRecipeView);
			viewList.add(viewObjRecipeView);
		}
		return viewList;
	}

	
	public IngredientViewImpl saveIngredient(Long id, IngredientViewImpl ingredient) {
		IngredientImpl impl = new IngredientImpl();
		BeanUtils.copyProperties(ingredient, impl);
		IngredientImpl savedIngredient =  ingredientService.saveIngredient(id,impl);
		IngredientViewImpl viewIngredient = new IngredientViewImpl();
		viewIngredient = convertToView(savedIngredient);
		return viewIngredient;
	}

	@Override
	public IngredientViewImpl findByName(String name, Long id) {
		Ingredient ingredient = ingredientService.findByName(name, id);
		IngredientViewImpl ingredientView = new IngredientViewImpl();
		BeanUtils.copyProperties(ingredient, ingredientView);
		return ingredientView;
	}

	@Override
	public void deleteIngredientById(Long id,IngredientViewImpl ingredient) {
		IngredientImpl impl = new IngredientImpl();
		BeanUtils.copyProperties(ingredient, impl);
		ingredientService.deleteIngredient(id, impl);
	}

	@Override
	public IngredientViewImpl updateIngredientById(Long id, IngredientViewImpl ingredient) {
		IngredientImpl impl = convertToImpl(ingredient);
		impl = ingredientService.updateIngredient(id, impl);
		ingredient = convertToView(impl);
		return ingredient;
	}

	@Override
	public IngredientViewImpl findById(Long id) {
		IngredientImpl impl = new IngredientImpl();
		impl = ingredientService.findById(id);
		return convertToView(impl);
	}

}
