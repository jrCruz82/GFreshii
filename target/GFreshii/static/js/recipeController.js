'use strict';

angular.module('RecipeListApp').controller('recipeController', ['$scope', '$log','recipeService', function($scope,  $log, recipeService) {

	var self = this;
	self.currentRecipe = {ingredients:[]};
	
	
	self.ingredients={};
	self.recipes = [];
	self.isDisabled = true;
	self.selectedRecipe;
	self.recipeNames =[];
	self.currentIndex = 0;
	self.currentIngredientId = 1;
	window.onload = function() {
		document.getElementById("rname").focus();
	}
	
	
	self.allRecipes = function(){
		recipeService.allRecipes().then(function(data){
			self.recipes = data;
			
		
		self.recipeNames = [];
		for(let i =0;i<self.recipes.length;i++){
			$log.log(self.recipes[i].name);
			self.recipeNames.push(self.recipes[i].name);
		}
		$log.log(self.recipeNames);		
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

	self.createRecipe = function(){
		
		recipeService.createRecipe(self.currentRecipe).then(self.allRecipes, function(errResponse){
			$log.error('Error while creating recipe');
		})
	}

	self.updateRecipeName = function(){
		for(let i =0;i<self.recipes.length;i++){
			if(self.recipes[i].name === self.recName){
				self.currentIndex = self.recipes[i].id;
				self.recName = '';
				
				$log.log(self.currentIndex);
				$log.log(self.currentRecipe);
						
			}
		}
	}
	
	self.createCurrentRecipeName = function() {
		
		for(let i =0;i<self.recipes.length;i++){
			$log.log(self.recipes[i].name);
			if(self.recipes[i].id === self.currentRecipe.id){
				
				$log.log(self.currentIndex);
				self.recName = self.currentRecipe["name"];
				self.recipes[i].name = self.currentRecipe["name"];
				$log.log('updating name');
				self.updateRecipe(self.currentRecipe,self.currentIndex);
				$log.log(self.currentRecipe);
				
				
			}else{
				continue;
			}
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
		self.currentRecipe.id = self.recipes[self.recipes.length-1].id +1;
		$log.log(self.currentRecipe.id);
	}
	
	self.removeIngredient = function(index) {
		for(let i=0;i<self.currentRecipe.ingredients.length;i++){
			self.currentRecipe.ingredients.splice(index,1);
			self.updateRecipe(self.currentRecipe, self.currentIndex);
		}
		
	};
	self.submitIngredient = function () {
		if(self.ingredients.id){
			for(let i =0;i<self.currentRecipe.ingredients.length;i++){
				if(self.currentRecipe.ingredients[i].id===self.ingredients.id){
					self.currentRecipe.ingredients[i] = (angular.copy(self.ingredients));
				}
			}
			self.updateRecipe(self.currentRecipe, self.currentIndex);
			self.ingredients = {};
			return;
		}
		self.ingredients.id = self.currentIngredientId;
		if(self.currentRecipe.ingredients === null || self.currentRecipe.ingredients === undefined){
			self.currentRecipe.ingredients = angular.copy(self.ingredients);
		}else{
			self.currentRecipe.ingredients.push(angular.copy(self.ingredients));
		}
		$log.log(self.currentRecipe.ingredients);
		$log.log('Recipe updated with id ', self.currentIndex);
        self.updateRecipe(self.currentRecipe, self.currentIndex);
		self.currentIngredientId++;
		self.ingredients = {};
		document.getElementById("amount").focus();
    }

	self.updateInstructions = function(){
		self.isDisabled = false;
		self.updateRecipe(self.currentRecipe, self.currentIndex);
		$log.log(self.currentRecipe.instruction);
	};
	
	self.loadRecipe = function(index){
		self.resetRecipe();
		self.currentIndex = self.recipes[index].id;
		self.currentIngredientId = self.recipes[index].ingredients.length +1;
		$log.log(self.currentIndex+' on loadRecipe');
		self.currentRecipe.id = self.recipes[index].id;
		self.currentRecipe = angular.copy(self.recipes[index]);
		$log.log(self.currentRecipe);
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