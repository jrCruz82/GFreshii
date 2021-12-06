package com.edbootcamp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edbootcamp.api.dao.IngredientDAO;
import com.edbootcamp.api.entity.Ingredient;
import com.edbootcamp.api.service.IngredientService;

@Service
public class IngredientServiceImpl implements IngredientService{

	@Autowired
	private IngredientDAO dao;
	
	@Override
	public List<Ingredient> fetchAllIngredientsByRecipe(Long id) {
		return dao.allIngredients(id);
	}

	@Override
	public Ingredient saveIngredient(Long id, Ingredient ingredient) {
		return dao.saveIngredient(id, ingredient);
	}

	@Override
	public void deleteIngredient(Long id, Ingredient ingredient) {
		dao.deleteIngredient(id, ingredient);
	}

	@Override
	public Ingredient findByName(String name, Long id) {
		return dao.findByName(name, id);
	}

	@Override
	public Ingredient updateIngredient(Long id, Ingredient impl) {
		return dao.updateIngredient(id,impl);
	}

	@Override
	public Ingredient findById(Long id) {
		return dao.findById(id);
	}

}
