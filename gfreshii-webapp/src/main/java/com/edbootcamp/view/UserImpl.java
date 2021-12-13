package com.edbootcamp.view;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserImpl {

	@JsonProperty("id")
	private Long id;
	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("userName")
	private String userName;
	@JsonProperty("email")
	private String email;
	@JsonProperty("password")
	private String password;
	@JsonProperty("recipes")
	@JsonIgnoreProperties(value= {"recipes"})
	private List<RecipeImpl> recipes;
	
	public UserImpl() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<RecipeImpl> getRecipes() {
		return recipes;
	}

	public void setRecipes(List<RecipeImpl> recipes) {
		this.recipes = recipes;
	}

	@Override
	public String toString() {
		return "UserImpl [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName
				+ ", email=" + email + ", recipes=" + recipes + ", password="+ password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
