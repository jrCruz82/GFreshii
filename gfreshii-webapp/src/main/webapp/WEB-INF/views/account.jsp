<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<title>Account</title>
</head>
<body ng-app="RecipeListApp" class="ng-cloak" ng-controller="recipeController as ctrl">
	<header>
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
			<a class="navbar-brand" href="#">GFreshii App</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNavDropdown">
				<ul class="navbar-nav">
					<li class="nav-item active">
						<a class="nav-link " href="/GFreshii/home">Home</a>
					</li>
				
				</ul>
			</div>
		</nav>
	</header>
	<main>
	<div class="p-3 text-center bg-light">
			<span style="display:none" id="user">${user}</span>
		    <h1 class="mb-3">${user.firstName}'s Account Settings</h1>
	</div>
	<table class="table table-hover table-striped table-bordered" id="userTable">
		<thead class="thead-dark">
		 	<tr>
		 		<th scope="col">Id</th>
                <th scope="col">First Name</th>
                <th scope="col">Last Name</th>
                <th scope="col">Username</th>
                <th scope="col">Email</th>
                <th scope="col">Password</th>
                <th scope="col">Edit</th>
                <th scope="col">Delete</th>
            </tr>
		</thead>
		<tbody>
			<tr>
				<td><input name="id" ng-model="ctrl.user.id"  ng-disabled="true"/> </td>
				<td><input name="firstName" ng-model="ctrl.user.firstName"  ng-disabled="!ctrl.enabledEdit"/> </td>
				<td><input name="lastName" ng-model="ctrl.user.lastName"  ng-disabled="!ctrl.enabledEdit"/> </td>
				<td><input name="userName" ng-model="ctrl.user.userName"  ng-disabled="true"/> </td>
				<td><input name="email" ng-model="ctrl.user.email"  ng-disabled="!ctrl.enabledEdit"/> </td>
				<td><input id="password" name="password" ng-model="ctrl.user.password"  ng-disabled="!ctrl.enabledEdit"/> </td>
				<td>
					<div>
						<button type="button" ng-hide="ctrl.enabledEdit" ng-click="ctrl.editUser()" class="btn btn-success custom-width" id="editUser">Edit</button>
						<button type="button" ng-show="ctrl.enabledEdit" ng-click="ctrl.userEdit()" class="btn btn-primary custom-width" id="userEdit">Submit</button>
					</div>
				</td>
				<td>
					<div>
						<input type="button" onclick="location.href='/GFreshii/home';" ng-click="ctrl.userDelete()" class="btn btn-danger custom-width" id="userDelete" value="Remove"/>
					</div>
				</td>
				</tr>
		</tbody>
	</table>
	<div class="alert alert-warning">
    	<strong>Warning!</strong> Removing user will remove all records!
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