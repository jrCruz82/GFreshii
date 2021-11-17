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
	private IngredientDAO iDao;
	
	@Override
	public List<Ingredient> fetchAllIngredientsByRecipe(Long id) {
		return iDao.allIngredients(id);
	}

	@Override
	public Ingredient saveIngredient(Long id, Ingredient ingredient) {
		return iDao.saveIngredient(id, ingredient);
	}

	@Override
	public void deleteIngredient(Long id, Ingredient ingredient) {
		iDao.deleteIngredient(id, ingredient);
	}

	@Override
	public Ingredient findByName(String name, Long id) {
		return iDao.findByName(name, id);
	}

	@Override
	public Ingredient updateIngredient(Long id, Ingredient impl) {
		return iDao.updateIngredient(id,impl);
	}

	@Override
	public Ingredient findById(Long id) {
		return iDao.findById(id);
	}

}
