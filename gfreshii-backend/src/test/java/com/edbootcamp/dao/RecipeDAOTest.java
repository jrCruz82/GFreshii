package com.edbootcamp.dao;

import org.testng.annotations.Test;

import com.edbootcamp.api.entity.Recipe;
import com.edbootcamp.entity.RecipeImpl;
import com.edbootcamp.entity.UserImpl;
import com.edbootcamp.service.RecipeServiceImpl;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
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
import org.testng.annotations.BeforeMethod;

@RunWith(MockitoJUnitRunner.class)
@Test
public class RecipeDAOTest {

	@Mock
	private RecipeDAOImpl recipeDAO;
	
	@InjectMocks
    private RecipeServiceImpl recipeService;
	
	@Spy
    private List<Recipe> recipes = new ArrayList<Recipe>();
	
	@Captor
    private ArgumentCaptor<RecipeImpl> captor = ArgumentCaptor.forClass(RecipeImpl.class);

	@BeforeMethod
	public void setup() {
		 MockitoAnnotations.initMocks(this);
	     recipes = getRecipeList();
	}

	
	@AfterMethod
	public void afterTest() {
		recipes.clear();
	}

	@Test
    public void saveRecipe(){
		recipeService.saveRecipe(recipes.get(1), 1L);
		verify(recipeDAO).saveRecipe(captor.capture(), eq(1L));
		Assert.assertEquals(captor.getValue().getName(), "Pizza");
		verify(recipes,times(3)).add(any(RecipeImpl.class));
    }
	
	@Test(expectedExceptions = RuntimeException.class)
    public void saveExistingRecipe() {
        doThrow(RuntimeException.class).when(recipeDAO).saveRecipe( recipes.get(0),eq(1L));
        recipeService.saveRecipe(any(RecipeImpl.class),eq(1L));
    }
	
	@Test
	public void findAllRecipes() {
        when(recipeDAO.allRecipes(1L)).thenReturn(recipes);
        Assert.assertEquals(recipeService.fetchAllRecipes(1L), recipes);
        verify(recipeDAO, times(1)).allRecipes(1L);
    }
	
	@Test
	public void deleteRecipe() {
		when(recipeDAO.findById(1L)).thenReturn(null);
        recipeService.deleteRecipeById(recipes.get(2).getId());
        verify(recipeDAO, times(1)).deleteRecipeById(recipes.get(2).getId());
    }
	
	@Test(expectedExceptions = RuntimeException.class)
    public void deleteRecipeNotExist() {
        doThrow(RuntimeException.class).when(recipeDAO).deleteRecipeById(recipes.get(2).getId());
        recipeService.deleteRecipeById(recipes.get(2).getId());
        verify(recipeService, atLeastOnce()).deleteRecipeById(recipes.get(2).getId());
    }
	
	private List<Recipe> getRecipeList() {
		
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
		
		RecipeImpl recipe2 = new RecipeImpl();
		recipe2.setId(2L);
		recipe2.setName("Pizza");
		recipe2.setInstruction("Set oven to 400");
		recipe2.setIngredients(null);
		recipe2.setUser(user1);
		
		RecipeImpl recipe3 = new RecipeImpl();
		recipe3.setId(3L);
		recipe3.setName("Pizza");
		recipe3.setInstruction("Set oven to 400");
		recipe3.setIngredients(null);
		recipe3.setUser(user1);
		
		recipes.add(recipe1);
		recipes.add(recipe2);
		recipes.add(recipe3);
		
		return recipes;
	}

}
