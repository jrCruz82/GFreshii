'use strict';


angular.module('RecipeListApp').factory('recipeService', ['$http','$q','$log', function($http, $q, $log) {
	
	var REST_SERVICE_URI = 'http://localhost:8080/GFreshii/recipes/';

	var factory = {
		createRecipe : createRecipe,
		allRecipes : allRecipes,
		updateRecipe : updateRecipe,
		deleteRecipe : deleteRecipe
		
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
	
	function createRecipe(recipe) {
		$log.log(recipe + " in service");
		let deferred = $q.defer();
		$http.post(REST_SERVICE_URI+'createRecipe', recipe).then(function(response) {
			deferred.resolve(response.data);
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
                $log.error('Error while deleting User');
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
