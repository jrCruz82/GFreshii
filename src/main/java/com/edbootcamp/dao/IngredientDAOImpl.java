package com.edbootcamp.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.edbootcamp.api.dao.IngredientDAO;
import com.edbootcamp.api.entity.Recipe;
import com.edbootcamp.api.manager.RecipeManager;
import com.edbootcamp.entity.IngredientImpl;
import com.edbootcamp.entity.RecipeImpl;
import com.edbootcamp.view.RecipeViewImpl;

@Repository
@EnableTransactionManagement
@Transactional
public class IngredientDAOImpl implements IngredientDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired 
	HibernateTemplate hibernateTemplate;
	
	@Autowired
	RecipeManager recipeManager;
	
	@Override
	public List<IngredientImpl> allIngredients(Long id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from IngredientImpl where recipeID=:id");
		query.setParameter("id", id);
		List<IngredientImpl> list = query.list();
		return list;
	}

	@Override
	public IngredientImpl saveIngredient(Long id, IngredientImpl ingredient) {
		RecipeImpl impl = hibernateTemplate.load(RecipeImpl.class, id);		
		ingredient.setRecipe( impl);
		hibernateTemplate.persist(ingredient);
		return ingredient;
	}


	@Override
	public void deleteIngredient(Long id, IngredientImpl ingredient ) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("Delete FROM IngredientImpl i WHERE i.id=:id").setLong("id", ingredient.getId());
		query.executeUpdate();
	}

	@Override
	public IngredientImpl findByName(String name, Long id) {
		Session session = sessionFactory.getCurrentSession();
		SQLQuery query = session.createSQLQuery("select * from INGREDIENT as i where i.NAME = :name and i.recipeID=:id");
		query.addEntity(IngredientImpl.class);
		query.setParameter("name",name);
		query.setLong("id",id);
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<IngredientImpl> list =  query.list();
		IngredientImpl ingredientImpl = list.get(0);
		return ingredientImpl;
	}

	@Override
	public IngredientImpl updateIngredient(Long id, IngredientImpl impl) {
		IngredientImpl ingredientImpl = hibernateTemplate.load(IngredientImpl.class, impl.getId());
		ingredientImpl.setAmount(impl.getAmount());
		ingredientImpl.setName(impl.getName());
		ingredientImpl.setUnit(impl.getUnit());
		hibernateTemplate.update(ingredientImpl);
		return ingredientImpl;
	}

	@Override
	public IngredientImpl findById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM IngredientImpl i WHERE i.id=:id").setLong("id", id);
		return (IngredientImpl) query.uniqueResult();
	}







	
}
