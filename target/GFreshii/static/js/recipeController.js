'use strict';

angular.module('RecipeListApp').controller('recipeController', ['$scope', '$log','recipeService', function($scope,  $log, recipeService) {

	var self = this;
	self.currentRecipe = {ingredients:[]};
	
	
	self.ingredients={};
	self.recipes = [];
	self.isDisabled = true;
	self.selectedRecipe;
	self.recipeNames =[];
	self.currentIndex = 1;
	self.currentIngredientId = 1;
	window.onload = function() {
		document.getElementById("rname").focus();
	}
	
	
	self.allRecipes = function(){
		recipeService.allRecipes().then(function(data){
			self.recipes = data;
			
		self.recipeNames = [];
		for(let i =0;i<self.recipes.length;i++){
			//$log.log(self.recipes[i].name);
			self.recipeNames.push(self.recipes[i].name);
		}	
		}, function(errResponse){
			$log.error('Error while fetching Recipes');
		});

	}
	self.allRecipes();
	
	self.deleteRecipe = function(id){
		recipeService.deleteRecipe(id)
            .then(
            self.allRecipes,
            function(errResponse){
                $log.error('Error while deleting User');
            }
        );
    }
	
	self.removeRecipe = function(){
		for(let i =0;i<self.recipes.length;i++){
			if(self.recipes[i].name === self.recName){
				self.currentIndex = self.recipes[i].id;
				$log.log(self.currentIndex);		
			}
			
		}
		self.resetRecipe();
		self.deleteRecipe(self.currentIndex);
	}
	
	self.updateRecipe =  function (currentRecipe, id){
        recipeService.updateRecipe(currentRecipe, id)
            .then(
            self.allRecipes,
            function(errResponse){
                $log.error('Error while updating Recipe');
            }
        );
    }

	self.addInstruction =  function (currentRecipe, id){
        recipeService.addInstruction(currentRecipe, id)
            .then(function(data){
			$log.log(data);
			},
            function(errResponse){
                $log.error('Error while updating Recipe');
            }
        );
    }

	self.loadIngredients =  function (id){
        recipeService.ingredientsByRecipe(id).then(function(data){
			$log.log(data);
			$log.log(' return data from loading ingredients');
			self.currentRecipe.ingredients = data;
		},
		    function(errResponse){
                $log.error('Error while loading ingredients');
            }
        );
    }
	
	self.createRecipe = function(){
		recipeService.createRecipe(self.currentRecipe).then(function(data){
			self.currentRecipe = data;
			self.currentIndex = self.currentRecipe["id"];
			self.allRecipes 
			self.loadIngredients(self.currentIndex);
		} , function(errResponse){
			$log.error('Error while creating recipe');
		});

	}

	self.addIngredient = function(){
		recipeService.addIngredient(self.ingredients,self.currentIndex).then(function(data){
			$log.log(data);
			
			$log.log(' data from adding ingredient');
			self.loadIngredients(self.currentIndex);
		},
			function(errResponse){
			$log.error('Error while adding Ingredient');
		});
		
	}
	
	self.updateIngredient = function(){
		recipeService.updateIngredient(self.ingredients,self.currentIndex).then(function(data){
			$log.log(data);
			
			
			$log.log(' data from adding ingredient');
			self.loadIngredients(self.currentIndex);
		},
			function(errResponse){
			$log.error('Error while adding Ingredient');
		});
		
	}
	
	self.deleteIngredient = function(){
		recipeService.deleteIngredient(self.currentIndex, self.delIngredient).then(self.allRecipes, function(errResponse){
			$log.error('Error while deleting Ingredient');
		});
	}
	
	self.deleteInstruction = function(){
		recipeService.deleteInstruction(self.currentIndex).then(self.allRecipes, function(errResponse){
			$log.error('Error while deleting Instructions');
		});
	}
	
	self.updateRecipeName = function(){
		if(self.currentRecipe.id === null || self.currentRecipe.id === undefined){
			$log.log(self.recipes);
			$log.log(self.recipes[self.recipes.length-1].id + ' id of last recipe');
			self.currentRecipe.id = self.recipes[self.recipes.length-1].id;
		}
		for(let i =0;i<self.recipes.length;i++){
			if(self.recipes[i].name === self.recName){
				self.currentIndex = self.recipes[i].id;
				self.recName = '';
				$log.log(self.currentIndex + ' id updating');			
			}
		}
	}
	
	self.createCurrentRecipeName = function() {
		if(self.currentIndex === self.currentRecipe["id"]){
			self.updateRecipe(self.currentRecipe,self.currentIndex);
			self.recName = self.currentRecipe["name"];
			return;
		}
		if(self.currentRecipe.name === null || self.currentRecipe.name === undefined){
			$log.error('No name was enetered for recipe');
			return;
		}
		self.recName = angular.copy(self.currentRecipe.name);
		$log.log(self.currentRecipe.name + " creating name");
		document.getElementById("amount").focus();
		self.createRecipe(self.currentRecipe);
	}
	
	self.removeIngredient = function(index) {
		$log.log(self.currentRecipe);
		$log.log(index + " ingredient index");
		self.delIngredient = self.currentRecipe.ingredients.splice(index,1);
		self.delIngredient = self.delIngredient[0];
		self.deleteIngredient(self.currentIndex, self.delIngredient);
		$log.log(self.delIngredient);
		
	};
	self.submitIngredient = function () {
		if(self.ingredients.id){
			$log.log(self.ingredients.id + ' id of ingredient being edited');
			for(let i =0;i<self.currentRecipe.ingredients.length;i++){
				if(self.currentRecipe.ingredients[i].id===self.ingredients.id){
					self.currentRecipe.ingredients[i] = (angular.copy(self.ingredients));
					self.currentRecipe.ingredients[i].recipe = self.currentIndex;
					$log.log(self.currentIndex + "recipe id");
					$log.log(self.currentRecipe.ingredients[i]);
				}
			}
			
			self.updateIngredient(self.currentRecipe, self.currentIndex);
			self.ingredients = {};
			self.allRecipes();
			return;
		}
		
		if(self.currentRecipe.ingredients === null || self.currentRecipe.ingredients === undefined){
			self.currentRecipe.ingredients = angular.copy(self.ingredients);
		}else{
			self.currentRecipe.ingredients.push(angular.copy(self.ingredients));
		}
		$log.log(self.currentRecipe.ingredients);
		$log.log('Recipe updated with id ', self.currentIndex);

		$log.log(self.ingredients);

		$log.log(self.currentIndex + " recipe id");
		$log.log(self.ingredients);
		self.addIngredient(self.ingredients, self.currentIndex);
		self.loadIngredients(self.currentIndex);
		self.ingredients = {};
		
		document.getElementById("amount").focus();
		
    }

	self.removeInstructions = function() {
		$log.log(self.currentRecipe);
		self.deleteInstruction(self.currentIndex);
		self.currentRecipe.instruction = "";
		//self.loadRecipe(self.currentIndex-1);
	};
	self.addInstructions = function(){
		self.isDisabled = false;
		if(self.currentRecipe === undefined){
			$log.error('Must enter Recipe name first');
			return;

		}
		self.addInstruction(self.currentRecipe, self.currentIndex);
		$log.log(self.currentRecipe.instruction);
	};
	
	self.loadRecipe = function(index){
		self.resetRecipe();
		self.allRecipes();
		self.currentIndex = self.recipes[index].id;
		$log.log(self.currentIndex+' on loadRecipe');
		
		self.currentRecipe = angular.copy(self.recipes[index]);
		$log.log(self.currentRecipe.instruction);
		try{
			self.currentIngredientId = self.currentRecipe.ingredients[self.currentRecipe.ingredients.length-1].id;
			
		}catch(e){
			self.currentIngredientId=1;
		}
		self.loadIngredients(self.currentIndex);
		
		self.recName = self.currentRecipe.name;
		self.newInstruction = angular.copy(self.currentRecipe.instruction);
	};
	
	self.resetRecipe = function(){
		self.currentRecipe = {ingredients:[]};
		self.recName = '';
		self.newInstruction = '';
		self.ingredients = {};
		self.currentIngredientId = 1;
	}
	self.edit = function(id){
        $log.log('id to be edited', id);
        for(let i = 0; i < self.currentRecipe.ingredients.length; i++){
            if(self.currentRecipe.ingredients[i].id === id) {
				
                self.ingredients = angular.copy(self.currentRecipe.ingredients[i]);
                break;
            }
        }
    }
	self.resetForm = function(){
		self.ingredients = {};
	}
}])