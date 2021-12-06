package com.edbootcamp.dao;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
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
import com.edbootcamp.entity.UserImpl;

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
	public List<Recipe> allRecipes(Long id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from RecipeImpl where USER_ID = :id").setParameter("id", id);
		Hibernate.initialize(query.list());
		List<Recipe> list = query.list();
		return list;
	}

	@Transactional
	@Override
	public Recipe saveRecipe(Recipe recipe, Long userId) {
		UserImpl userImpl = hibernateTemplate.load(UserImpl.class, userId);
		recipe.setUser(userImpl);
		hibernateTemplate.persist(recipe);
		return recipe;
	}
	
	@Transactional
	@Override
	public Recipe getIngredientsById(Recipe recipe) {
		return hibernateTemplate.get(RecipeImpl.class, recipe.getId());
	}
	@Transactional
	@Override
	public Recipe findById(Long id) {
		Recipe recipe = (RecipeImpl)hibernateTemplate.get(RecipeImpl.class, id);
		return recipe;
	}
	@Transactional
	@Override
	public Recipe updateRecipe(Recipe currentRecipe) {
		Recipe recipeImpl = hibernateTemplate.load(RecipeImpl.class, currentRecipe.getId());
		recipeImpl.setName(currentRecipe.getName());
		hibernateTemplate.update(recipeImpl);
		return recipeImpl;
	}
	@Transactional
	@Override
	public void deleteRecipeById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("Delete FROM RecipeImpl r WHERE r.id=:id").setLong("id", id);
		query.executeUpdate();
		
	}
	@Transactional
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
	@Transactional
	@Override
	public Recipe addInstruction(Long id, Recipe impl) {
		Recipe recipeImpl = hibernateTemplate.load(RecipeImpl.class, id);
		recipeImpl.setInstruction(impl.getInstruction());
		hibernateTemplate.update(recipeImpl);
		return recipeImpl;
	}
	@Transactional
	@Override
	public void deleteRecipeInstructionById(Long id) {
		Recipe recipeImpl = hibernateTemplate.load(RecipeImpl.class, id);
		recipeImpl.setInstruction(null);
	}


}
