<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">


<title>Grocery List login Page</title>

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
				</ul>
			</div>
		</nav>
	</header>
	<main>
		<div class="p-3 text-center bg-light">
		    <h1 class="mb-3">Welcome to GFreshii</h1>
		    <h4 class="mb-3">Enter your user information to login</h4>
		  </div>
  			<form action="/GFreshii/user/login" name="myForm" class="form-horizontal" method="post">
   			  <div class="form-row justify-content-center">
			  	<div class="form-group col-md-4">
			    <label for="inputUserName">UserName</label>
			    <input type="text" name="userName" class="form-control" id="inputUserName" placeholder="UserName" required>
			  	</div>
			  </div>
			  <div class="form-row justify-content-center">
			  	<div class="form-group col-md-4">
			    <label for="inputPassword">Password</label>
			    <input type="password" name="password" class="form-control" id="inputPassword" placeholder="Password" required>
			  </div>
			  </div>
			  <div class="form-row justify-content-center">
			    <button type="submit" class="btn btn-primary">Sign in</button>
			  </div>
		</form>
	</main>
	

	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	<script	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular.min.js"></script>
</body>
</html>
