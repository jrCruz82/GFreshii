package com.edbootcamp.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.edbootcamp.api.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "USER")
public class UserImpl implements User{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	@Column(name = "FIRSTNAME", nullable = false)
	private String firstName;
	@Column(name = "LASTNAME", nullable = false)
	private String lastName;
	@Column(name = "USERNAME", nullable = false)
	private String userName;
	@Column(name = "EMAIL", nullable = false)
	private String email;
	@Column(name = "PASSWORD")
	private String password;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade =CascadeType.ALL, orphanRemoval = false)
	@JsonIgnore
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
//	@Override
//	public String toString() {
//		return "UserImpl [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName
//				+ ", email=" + email + ", recipes=" + recipes + "]";
//	}
}
