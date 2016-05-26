/* Adquiere el valor de los parametros de la peticion GET */
function getQueryVariable(variable) {
	var query = window.location.search.substring(1);
	var vars = query.split("&");
	for (var i = 0; i < vars.length; i++) {
		var pair = vars[i].split("=");
		if (pair[0] == variable) {
			return pair[1];
		}
	}
	return (false);
}

/**/
var myapp = angular.module('destapp', []);

myapp.controller('destcontoller', function($scope, $http) {

	$scope.showData = function() {

		$scope.curPage = 0;
		$scope.pageSize = 8;
		var val = getQueryVariable("carreraId");
		$http.get("/ProyectoSoftware/MostrarDestino?carreraId=" + val).then(
				function(response) {
					$scope.datalists = response.data.destino;
				})

		$scope.numberOfPages = function() {
			return Math.ceil($scope.datalists.length / $scope.pageSize);
		};

	}

	$scope.predicate = 'idAsignatura'; 	// Por defecto ordena por idAsignatura
	$scope.reverse = true;
	$scope.order = function(predicate) {
		$scope.reverse = ($scope.predicate === predicate) ? !$scope.reverse
				: false;
		$scope.predicate = predicate;
	};

});

angular.module('destapp').filter('pagination', function() {
	return function(input, start) {
		start = +start;
		return input.slice(start);
	};
});
