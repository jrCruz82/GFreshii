package com.edbootcamp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edbootcamp.api.dao.IngredientDAO;
import com.edbootcamp.api.service.IngredientService;
import com.edbootcamp.entity.IngredientImpl;

@Service("ingredientService")
public class IngredientServiceImpl implements IngredientService{

	@Autowired
	private IngredientDAO iDao;
	
	@Override
	public List<IngredientImpl> fetchAllIngredientsByRecipe(Long id) {
		return iDao.allIngredients(id);
	}

	@Override
	public IngredientImpl saveIngredient(Long id, IngredientImpl ingredient) {
		return iDao.saveIngredient(id, ingredient);
	}

	@Override
	public void deleteIngredient(Long id, IngredientImpl ingredient) {
		iDao.deleteIngredient(id, ingredient);
	}

	@Override
	public IngredientImpl findByName(String name, Long id) {
		return iDao.findByName(name, id);
	}

	@Override
	public IngredientImpl updateIngredient(Long id, IngredientImpl impl) {
		return iDao.updateIngredient(id,impl);
	}

	@Override
	public IngredientImpl findById(Long id) {
		return iDao.findById(id);
	}

}
