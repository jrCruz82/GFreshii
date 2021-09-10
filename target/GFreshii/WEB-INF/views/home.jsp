<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en-us">
<head>

<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">


<title>Grocery List Home Page</title>

</head>
<body>
	<header>
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
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
				</ul>
			</div>
		</nav>
	</header>
	<main>
		<h1>Welcome to GFreshii</h1>
		<h2>Enter your user information to get started</h2>
		<div class="generic-container">
    	<div class="panel panel-default">
    		<div class="formcontainer">
    			<form action="createUser" name="myForm" class="form-horizontal">
	    			<div class="row">
	                	<div class="form-group col-md-12">
	    						<label class="col-md-2 control-lable">First Name: </label>
								<div class="col-md-7">	
									<input type="text" name="firstName" class="username form-control input-sm" placeholder="First Name" required>
								</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-12">		
							<label class="col-md-2 control-lable">Last Name:  </label>
							
							<div class="col-md-7">
								<input type="text" name="lastName" class="username form-control input-sm" placeholder="Last Name" required><br>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable">User Name: </label>
							
							<div class="col-md-7">
								<input type="text" name="userName" class="username form-control input-sm" placeholder="Username" required><br>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable">Email: </label>
							
							<div class="col-md-7">
								<input type="email" name="email" class="username form-control input-sm" placeholder="Email" required><br>
							</div>
							<div class="form-actions floatRight">
								<input type="submit" class="btn btn-primary btn-sm">
							</div>
						</div>
						
	                    	
						
					</div>
					
					
				</form>
					
			</div>
		</div>
	</div>
	</main>
	

	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	<script	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular.min.js"></script>
</body>
</html>
