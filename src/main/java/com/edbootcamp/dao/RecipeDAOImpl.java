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
	public List<Recipe> allRecipes() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from RecipeImpl");
		List<?> list = query.list();
		return (List<Recipe>) list;
	}

	@Override
	public Recipe saveRecipe(Recipe recipe) {
		Long id = (Long) hibernateTemplate.save(recipe);
		recipe.setId(id);
		return recipe;
	}
	
	public Recipe getIngredientsById(Recipe recipe) {
		return hibernateTemplate.get(RecipeImpl.class, recipe.getId());
	}
	
	public Recipe findById(Long id) {
		return hibernateTemplate.get(RecipeImpl.class, id);
	}

	@Override
	public Recipe updateRecipe(Recipe currentRecipe) {
		Recipe recipeImpl = hibernateTemplate.load(RecipeImpl.class, currentRecipe.getId());
		recipeImpl.setName(currentRecipe.getName());
		hibernateTemplate.update(recipeImpl);
		return recipeImpl;
	}

	@Override
	public void deleteRecipeById(Long id) {
		Recipe delRecipe = findById(id);
		hibernateTemplate.delete(delRecipe);
	}

	@Override
	public Boolean isRecipeExist(Recipe recipe) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM RecipeImpl r where r.name = :name").setParameter("name", recipe.getName());
		List<Recipe> list = query.list();
		if(list.isEmpty()) {
			return false;
		}
		return true;
	}

	@Override
	public Recipe addInstruction(Long id, Recipe impl) {
		Recipe recipeImpl = hibernateTemplate.load(RecipeImpl.class, id);
		recipeImpl.setInstruction(impl.getInstruction());
		hibernateTemplate.update(recipeImpl);
		return recipeImpl;
	}

	@Override
	public void deleteRecipeInstructionById(Long id) {
		Recipe recipeImpl = hibernateTemplate.load(RecipeImpl.class, id);
		recipeImpl.setInstruction(null);
	}


}
