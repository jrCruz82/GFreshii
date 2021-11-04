package com.edbootcamp.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import com.edbootcamp.api.dao.RecipeDAO;
import com.edbootcamp.api.entity.Recipe;
import com.edbootcamp.entity.RecipeImpl;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
@EnableTransactionManagement
@Transactional
public class RecipeDAOImpl implements RecipeDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	HibernateTemplate hibernateTemplate;

	@Transactional
	@SuppressWarnings({ "unchecked" })
	@Override
	public List<RecipeImpl> allRecipes() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from RecipeImpl");
		List<?> list = query.list();
		return (List<RecipeImpl>) list;
	}

	@Override
	public RecipeImpl saveRecipe(RecipeImpl recipe) {
		Long id = (Long) hibernateTemplate.save(recipe);
		recipe.setId(id);
		return recipe;
	}

	public boolean isRecipeExist(Recipe recipe) { 
		return hibernateTemplate.contains(recipe.getName());
	}
	
	public RecipeImpl getIngredientsById(RecipeImpl recipe) {
		return hibernateTemplate.get(RecipeImpl.class, recipe.getId());
	}
	
	public RecipeImpl findById(Long id) {
		return hibernateTemplate.get(RecipeImpl.class, id);
	}

	@Override
	public RecipeImpl updateRecipe(RecipeImpl currentRecipe) {
//		ObjectMapper mapper = new ObjectMapper();
//    	mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		RecipeImpl recipeImpl = hibernateTemplate.load(RecipeImpl.class, currentRecipe.getId());
		recipeImpl.setName(currentRecipe.getName());
		hibernateTemplate.update(recipeImpl);
		return recipeImpl;
	}

	@Override
	public void deleteRecipeById(Long id) {
		RecipeImpl delRecipe = findById(id);
		hibernateTemplate.delete(delRecipe);
	}

	@Override
	public boolean isRecipeExist(RecipeImpl recipe) {
		return hibernateTemplate.contains(recipe);
	}

	@Override
	public RecipeImpl addInstruction(Long id, RecipeImpl impl) {
		RecipeImpl recipeImpl = hibernateTemplate.load(RecipeImpl.class, id);
		recipeImpl.setInstruction(impl.getInstruction());
		hibernateTemplate.update(recipeImpl);
		return recipeImpl;
	}

	@Override
	public void deleteRecipeInstructionById(Long id) {
		RecipeImpl recipeImpl = hibernateTemplate.load(RecipeImpl.class, id);
		recipeImpl.setInstruction(null);
	}


}
