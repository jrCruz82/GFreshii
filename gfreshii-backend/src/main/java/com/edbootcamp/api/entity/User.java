package com.edbootcamp.api.entity;

import java.util.List;

import com.edbootcamp.entity.RecipeImpl;

public interface User {

	Long getId();
	void setId(Long id);
	String getFirstName();
	void setFirstName(String firstName);
	String getLastName();
	void setLastName(String lastName);
	String getUserName();
	void setUserName(String userName);
	String getEmail();
	void setEmail(String email);
	List<RecipeImpl> getRecipes();
	void setRecipes(List<RecipeImpl> recipes);
	String getPassword();
	void setPassword(String password);
}
