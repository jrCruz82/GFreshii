<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<title>Recipes</title>

</head>

<body ng-app="RecipeListApp" class="ng-cloak" ng-controller="recipeController as ctrl">
	
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark ">
		<a class="navbar-brand" href="#">GFreshii App</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNavDropdown">
			<ul class="navbar-nav">
				<li class="nav-item active"> 
					<a class="nav-link " href="home">Home</a>
				</li>
				<li class="nav-item active">
					<a class="nav-link " href="recipe">Recipes</a>
				</li>
				<li class="nav-item active">
					<a class="nav-link " href="groceries">Grocery List</a>
				</li>
			    <li class="nav-item dropdown ">
			    	<button class="btn btn-secondary dropdown-toggle " type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">Edit Recipe<span class="caret"></span></button>
			       	<ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="dropdownMenu1" ng-hide="!ctrl.recipeNames">
            			<li ng-repeat="name in ctrl.recipeNames track by $index "><a class="dropdown-item " type="button" ng-click="ctrl.loadRecipe($index)">{{name}}</a></li>
            		</ul>
			    </li>
			</ul>
		</div>

	</nav>
	<main>
		<div class="p-3 text-center bg-light">
		    <h1 class="mb-3">Recipe Form</h1>
		</div>
      <div class="generic-container">
          <div class="panel panel-default">
              <div class="formcontainer ">
                  <form name="myForm" class="form-horizontal"  >
                      <input  type="hidden" ng-model="ctrl.currentRecipe.id" />
                      <div class="row " align="center">
                          <div class="form-group col-md-12 " >
                              <label ng-hide="ctrl.recName" class="col-md-2 control-lable " for="rname">Recipe Name</label>
                              <div ng-hide="!ctrl.recName">
								<button type="button" class="btn btn-primary" ng-click="ctrl.resetRecipe()" >Create New Recipe</button>
								<button type="button" class="btn btn-danger" ng-click="ctrl.removeRecipe()" >Delete Recipe</button>
								<button type="button" class="btn btn-secondary" ng-click="ctrl.updateRecipeName()" >Edit Recipe Name</button>
							  </div>
                              <div class="col-md-7 shadow-lg bg-white rounded" align="center">
                                  <input  ng-hide="ctrl.recName" type="text" ng-model="ctrl.currentRecipe.name" id="rname" class="rname form-control " placeholder="Enter recipe name" required ng-minlength="3" />
                                  <button ng-hide="ctrl.recName" ng-click="ctrl.createCurrentRecipeName()" ng-disabled="ctrl.recName || !ctrl.currentRecipe.name" class="btn btn-primary hover-shadow">Add Name</button>
                                  
                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.rname.$error.required">This is a required field</span>
                                      <span ng-show="myForm.rname.$error.minlength">Minimum length required is 3</span>
                                      <span ng-show="myForm.rname.$invalid">This field is invalid </span>
                                  </div>
                              </div>
                          </div>
                      </div>
                         
                     <div>
                     	<h1 style="text-align: center">{{ctrl.recName}}</h1>
                     	<br>
                     </div>
                      <div class="row justify-content-center">
                      
                          <div class="form-group col-md-2 ">
                           		
	                          <input type="text" ng-model='ctrl.ingredients["amount"]' id="amount" class="amount form-control input-sm" placeholder="Ingredient amount" required/>
	                          <div class="has-error" ng-show="myForm.$dirty">
	                              <span ng-show="myForm.amount.$error.required">This is a required field</span>
	                              <span ng-show="myForm.amount.$invalid">This field is invalid </span>
	                          </div>
                             
                          </div>
                          <div class="form-group col-md-2">
                              <input type="text" ng-model='ctrl.ingredients["unit"]' id="unit" class="unit form-control input-sm" placeholder="Ingredient units"/>
                          </div>							
                          <div class="form-group col-md-3">
                              <input type="text" ng-model='ctrl.ingredients["name"]' id="name" class="name form-control input-sm" placeholder="Ingredient name" required/>
                              <div class="has-error" ng-show="myForm.$dirty">
                              	<span ng-show="myForm.iname.$error.required">This is a required field</span>
                                <span ng-show="myForm.iname.$invalid">This field is invalid </span>
                              </div>
                          </div>
                          <div class="form-actions floatRight">
                              <input type="submit" ng-click="ctrl.submitIngredient(ctrl.currentRecipe.id)" value="{{!ctrl.ingredients.id ? 'Add Ingredient' : 'Update Ingredient'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                              <button type="button" ng-click="ctrl.resetForm()" class="btn btn-secondary btn-sm" ng-disabled="myForm.$pristine">Reset Form</button>
                          </div>
                          
                      </div>
                      <br>
                      <br>
                      <div class="row justify-content-center">
						  <div >
							  <label class="form-label" for="instruction" style="vertical-align: top">Instructions</label>
							  <textarea class="form-control" name="insName" id="instruction" rows="7" cols="145" placeholder="Enter cooking instructions" ng-model="ctrl.currentRecipe.instruction"></textarea>
					  			<div>
									<div class="has-error" ng-show="instructions.$dirty">
										<span ng-show="insName.instructions.$error.requiered">Must fill in instructions</span>
									</div>
									<button class="btn btn-primary" ng-click="ctrl.addInstructions()" ng-disabled="!ctrl.currentRecipe.name || !ctrl.currentRecipe.instruction">Submit/Edit Recipe Instructions</button>
									<button type="button" ng-click="ctrl.removeInstructions()" class="btn btn-danger custom-width">Delete Instructions</button>
								</div>
						  </div>
					 </div>
                  </form>
              </div>
          </div>
          <div class="panel panel-default col-md-12 ">
              <div class="panel-heading"><span class="lead">List of Ingredients </span></div>
              <div class="tablecontainer justify-content-center">
                  <table class="table table-hover table-striped table-bordered">
                  	  <thead class="thead-dark">
                          <tr>
                              <th scope="col">Id</th>
                              <th scope="col">Amount</th>
                              <th scope="col">Unit</th>
                              <th scope="col">Name</th>
                              <th scope="col">Edit/Remove</th>
                          </tr>
                      </thead>
                      <tbody>
                          <tr ng-repeat="u in ctrl.currentRecipe.ingredients track by $index">
                              <td><span ng-bind="u.id"></span></td>
                              <td><span ng-bind="u.amount"></span></td>
                              <td><span ng-bind="u.unit"></span></td>
                              <td><span ng-bind="u.name"></span></td>
                              <td>
                              <button type="button" ng-click="ctrl.edit(u.id)" class="btn btn-success custom-width">Edit</button>
                              <button type="button" ng-click="ctrl.removeIngredient($index)" class="btn btn-danger custom-width">Remove</button>
                              </td>
                          </tr>
                      </tbody>
                  </table>
              </div>
          </div>
      </div>
	</main>


	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	<script	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular.min.js"></script>
	<script src="<c:url value='/static/js/app.js' />"></script>
	<script src="<c:url value='/static/js/recipeController.js' />"></script>
	<script src="<c:url value='/static/js/recipeService.js' />"></script>

</body>
</html>