package com.edbootcamp.dao;

import org.testng.annotations.Test;

import com.edbootcamp.api.entity.Ingredient;
import com.edbootcamp.api.entity.Recipe;
import com.edbootcamp.entity.IngredientImpl;
import com.edbootcamp.entity.RecipeImpl;
import com.edbootcamp.entity.UserImpl;
import com.edbootcamp.service.IngredientServiceImpl;
import com.edbootcamp.service.RecipeServiceImpl;

import org.testng.annotations.BeforeMethod;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

@RunWith(MockitoJUnitRunner.class)
@Test
public class IngredientDAOTest {

	@Mock
	private IngredientDAOImpl ingredientDAO;
	
	@InjectMocks
    private IngredientServiceImpl ingredientService;
	
	@Spy
    private List<Ingredient> ingredients = new ArrayList<Ingredient>();
	
	@Captor
    private ArgumentCaptor<IngredientImpl> captor = ArgumentCaptor.forClass(IngredientImpl.class);
	
	@BeforeMethod
	public void setup() {
		 MockitoAnnotations.initMocks(this);
	     ingredients = getIngredientList();
	}

	
	@AfterMethod
	public void afterMethod() {
		ingredients.clear();
	}

	@Test
    public void saveIngredient(){
		ingredientService.saveIngredient( 1L, ingredients.get(1));
		verify(ingredientDAO).saveIngredient(eq(1L), captor.capture());
		Assert.assertEquals(captor.getValue().getName(), "Tomato Sauce");
		verify(ingredients,times(3)).add(any(IngredientImpl.class));
    }
	
	@Test(expectedExceptions = RuntimeException.class)
    public void saveExistingIngredient() {
        doThrow(RuntimeException.class).when(ingredientDAO).saveIngredient(eq(1L), ingredients.get(0));
        ingredientService.saveIngredient(eq(1L),any(IngredientImpl.class));
    }
	
	@Test
	public void allIngredients() {
        when(ingredientService.fetchAllIngredientsByRecipe(1L)).thenReturn(ingredients);
        Assert.assertEquals(ingredientService.fetchAllIngredientsByRecipe(1L), ingredients);
        verify(ingredientDAO, times(1)).allIngredients(1L);
    }
	
	@Test
	public void deleteIngredient() {
		when(ingredientDAO.findById(1L)).thenReturn(null);
		ingredientService.deleteIngredient(ingredients.get(2).getId(), ingredients.get(2));
        verify(ingredientDAO, times(1)).deleteIngredient(ingredients.get(2).getId(),ingredients.get(2));
    }
	
	@Test(expectedExceptions = RuntimeException.class)
    public void deleteIngredientNotExist() {
        doThrow(RuntimeException.class).when(ingredientDAO).deleteIngredient(ingredients.get(2).getId(),ingredients.get(2));
        ingredientService.deleteIngredient(ingredients.get(2).getId(),ingredients.get(2));
        verify(ingredientService, atLeastOnce()).deleteIngredient(ingredients.get(2).getId(),ingredients.get(2));
    }
	private List<Ingredient> getIngredientList() {
		
		UserImpl user1 = new UserImpl();
		user1.setId(1L);
		user1.setFirstName("Jose");
		user1.setLastName("Cruz");
		user1.setUserName("MrRecipe");
		user1.setEmail("test@test.com");
		user1.setPassword("123456");
		
		RecipeImpl recipe1 = new RecipeImpl();
		recipe1.setId(1L);
		recipe1.setName("Pizza");
		recipe1.setInstruction("Set oven to 400");
		recipe1.setIngredients(null);
		recipe1.setUser(user1);
		
		IngredientImpl ingredient1 = new IngredientImpl();
		ingredient1.setId(1L);
		ingredient1.setName("Pepperoni");
		ingredient1.setAmount("1");
		ingredient1.setUnit("cups");
		ingredient1.setRecipe(recipe1);
		
		IngredientImpl ingredient2 = new IngredientImpl();
		ingredient2.setId(2L);
		ingredient2.setName("Tomato Sauce");
		ingredient2.setAmount("1");
		ingredient2.setUnit("cups");
		ingredient2.setRecipe(recipe1);
		
		IngredientImpl ingredient3 = new IngredientImpl();
		ingredient3.setId(3L);
		ingredient3.setName("Mozzarella cheese");
		ingredient3.setAmount("2");
		ingredient3.setUnit("cups");
		ingredient3.setRecipe(recipe1);
		
		ingredients.add(ingredient1);
		ingredients.add(ingredient2);
		ingredients.add(ingredient3);
		
		return ingredients;
	}

}
