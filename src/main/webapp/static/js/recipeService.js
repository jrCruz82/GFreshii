'use strict';


angular.module('RecipeListApp').factory('recipeService', ['$http','$q','$log', function($http, $q, $log) {
	
	var REST_SERVICE_URI = 'recipes/';

	var factory = {
		createRecipe : createRecipe,
		allRecipes : allRecipes,
		updateRecipe : updateRecipe,
		deleteRecipe : deleteRecipe,
		ingredientsByRecipe : ingredientsByRecipe,
		addIngredient : addIngredient,
		deleteIngredient : deleteIngredient,
		updateIngredient : updateIngredient,
		addInstruction : addInstruction,
		deleteInstruction : deleteInstruction
	};
	
	return factory;
	
	function allRecipes() {
		let deferred = $q.defer();
		$http.get(REST_SERVICE_URI).then(function(response){
			deferred.resolve(response.data);
		}, function(errResponse){
			$log.error('Error while fetching Recipes...');
			deferred.reject(errResponse);
		});
		return deferred.promise;
	}
	
	function addInstruction(currentRecipe,id) {
		let deferred = $q.defer();
		$http.put(REST_SERVICE_URI+'addInstruction/'+id,currentRecipe ).then(function(response){
			deferred.resolve(response.data);
		}, function(errResponse){
			$log.error('Error while adding Instructions...');
			deferred.reject(errResponse);
		});
	return deferred.promise;
	}
	
	function ingredientsByRecipe(id) {
		let deferred = $q.defer();
		$http.get(REST_SERVICE_URI+'ingredientsByRecipe/'+id ).then(function(response){
			deferred.resolve(response.data);
		}, function(errResponse){
			$log.error('Error while fetching Ingredients...');
			deferred.reject(errResponse);
		});
		return deferred.promise;
	}
	
	function addIngredient(ingredient,id) {
		let deferred = $q.defer();
		$http.post(REST_SERVICE_URI+'addIngredient/'+id, ingredient ).then(function(response){
			deferred.resolve(response.data);
			$log.log(response.data);
			$log.log(' return from adding ingredient in service');
		}, function(errResponse){
			$log.error('Error while adding Ingredients...');
			deferred.reject(errResponse);
		});
		return deferred.promise;
	}
	
	function createRecipe(recipe) {
		let deferred = $q.defer();
		$http.post(REST_SERVICE_URI+'createRecipe', recipe).then(function(response) {
			deferred.resolve(response.data);
			$log.log(response.data);
		}, function(errResponse){
			$log.error('Error while creating Recipe');
			deferred.reject(errResponse);
		});
		return deferred.promise;
	}

	function deleteRecipe(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+'deleteRecipe/'+id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                $log.error('Error while deleting Recipe');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

	
	function deleteInstruction(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+'deleteInstruction/'+id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                $log.error('Error while deleting Instructions');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

	function deleteIngredient(id,ingredient) {
        var deferred = $q.defer();
		$log.log(ingredient);
		$log.log(id);
        $http.put(REST_SERVICE_URI+'deleteIngredient/'+id,ingredient).then(function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                $log.error('Error while deleting Ingredient');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
	
	function updateIngredient(recipe, id) {
		$log.log(id+ ' in service')
		$log.log(recipe.name + ' in service');
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+'updateIngredient/'+id, recipe)
            .then(
            function (response) {
				$log.log(response+ ' in service')
                deferred.resolve(response.data);
            },
            function(errResponse){
                $log.error('Error while updating Ingredient');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
	
	function updateRecipe(recipe, id) {
		$log.log(id+ ' in service')
		$log.log(recipe.name + ' in service');
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+'updateRecipe/'+id, recipe)
            .then(
            function (response) {
				$log.log(response+ ' in service')
                deferred.resolve(response.data);
            },
            function(errResponse){
                $log.error('Error while updating Recipe');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
}]);
