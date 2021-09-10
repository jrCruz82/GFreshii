package com.edbootcamp.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import com.edbootcamp.model.Ingredient;
import com.edbootcamp.model.Recipe;

@Repository
public class RecipeDAOImpl extends AbstractDao<Integer, Recipe> implements RecipeDAO {
	
	private static Long recipeId= (long) 0;
	
	
	private static List<Recipe> recipes = new ArrayList<>();
	private static List<Ingredient> ingredients = new ArrayList<>();
	private static String instructions;
	
	static {
		
		ingredients = populateIngredients();
		
		instructions = populateInstructions();
		recipes.add(populateRecipes());
		
	}
	
	private static Recipe populateRecipes(){
		
		Recipe recipe1 = new Recipe();
		recipe1.setId(++recipeId);
		recipe1.setName("Pizza");
		recipe1.setIngredients(ingredients);
		recipe1.setInstruction(instructions);
		return recipe1;
	}
	
	private static List<Ingredient> populateIngredients(){
		System.out.println("before");
		Long ingredientId = (long) 0;
		int index = 0;
		
		List<Ingredient> ingredients = new ArrayList<>();
		System.out.println("after");
		Ingredient ingredient1 = new Ingredient();
		ingredient1.setId(++ingredientId);
		ingredient1.setName("refrigerated pizza dough");
		ingredient1.setAmount("1");
		ingredient1.setUnit("pound");
		ingredients.add(index,ingredient1);
		index++;
		
		Ingredient ingredient2 = new Ingredient();
		ingredient2.setId(++ingredientId);
		ingredient2.setName("pizza sauce");
		ingredient2.setAmount("1/4");
		ingredient2.setUnit("cup");	
		ingredients.add(index,ingredient2);
		index++;
		
		Ingredient ingredient3 = new Ingredient();
		ingredient3.setId(++ingredientId);
		ingredient3.setName("shredded mozzarella cheese");
		ingredient3.setAmount("1/2");
		ingredient3.setUnit("cup");	
		ingredients.add(index,ingredient3);
		index++;
		
		Ingredient ingredient4 = new Ingredient();
		ingredient4.setId(++ingredientId);
		ingredient4.setName("sliced pepperoni");
		ingredient4.setAmount("1/2");
		ingredient4.setUnit("cup");	
		ingredients.add(index,ingredient4);
		index++;
		System.out.println(ingredients.size() + " dao");
		return ingredients;
	}
	private static String populateInstructions() {
		
		String instructions1 = new String();
		instructions1 = "Place pizza stone on grill directly over wood fire."
				+ " You may need to begin by spreading out the wood if the flames are too tall."
				+ " Roll out the pizza dough to desired thickness. Place it on the pizza stone "
				+ "and cook 10 minutes on one side until golden. Remove from the fire and on the "
				+ "cooked side, spread the pizza sauce in an even layer over dough leaving about "
				+ "a half inch around the rim of the pizza dough bare. Sprinkle mozzarella cheese "
				+ "evenly on top of the sauce, followed by the pepperoni slices. Place uncooked side "
				+ "down, back on the pizza stone. Cover with a foil tent and cook until cheese has "
				+ "melted, about 10 minutes more. Transfer pizza to a cutting board and let cool "
				+ "slightly before cutting and serving.";
		
		return instructions1;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Recipe> allRecipes() {
		Criteria criteria = createEntityCriteria();
		return (List<Recipe>)criteria.list();
	}
	
	public Recipe saveRecipe(Recipe recipe) {
		persist(recipe);
		return recipe;
	}
	
	public boolean isRecipeExist(Recipe recipe) {
		return findByName(recipe.getName()) != null;
	}
	
	private Recipe findByName(String name) {
		for(Recipe recipe: recipes) {
			if(recipe.getName().equals(name)) {
				return recipe;
			}
		}
		return null;
	}
	
	public Recipe findById(long id) {
		for(Recipe recipe: recipes) {
			if(recipe.getId() == id) {
				return recipe;
			}
		}
		return null;
	}
	
	public Recipe updateRecipe(Recipe currentRecipe) {
		int index = recipes.indexOf(currentRecipe);
		recipes.set(index, currentRecipe);
		return currentRecipe;
	}

	
	public void deleteRecipeById(Long id) {
		recipes.remove(findById(id));
	}
	
}
