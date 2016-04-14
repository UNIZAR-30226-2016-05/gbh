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
var myapp = angular.module('sampleapp', []);

myapp.controller('samplecontoller', function($scope, $http) {

	$scope.showData = function() {

		$scope.curPage = 0;
		$scope.pageSize = 8;
		var val = getQueryVariable("navCarrera");
		$http.get("/ProyectoSoftware/BuscaDestino?navCarrera=" + val).then(
				function(response) {
					$scope.datalists = response.data.destino;
				})

		$scope.numberOfPages = function() {
			return Math.ceil($scope.datalists.length / $scope.pageSize);
		};

	}

	$scope.predicate = 'Universidad'; 	// Por defecto ordena por Universidad
	$scope.reverse = true;
	$scope.order = function(predicate) {
		$scope.reverse = ($scope.predicate === predicate) ? !$scope.reverse
				: false;
		$scope.predicate = predicate;
	};
	
	
    $scope.filter = {};

    $scope.getCategories = function () {
        return ($scope.datalists || []).map(function (w) {
            return w.Idioma;
        }).filter(function (w, idx, arr) {
            return arr.indexOf(w) === idx;
        });
    };
    
    $scope.getPais = function () {
        return ($scope.datalists || []).map(function (w) {
            return w.Pais;
        }).filter(function (w, idx, arr) {
            return arr.indexOf(w) === idx;
        });
    };
    
    $scope.filterByCategory = function (variable) {
        return $scope.filter[variable.Idioma] || noFilter($scope.filter) || $scope.filter[variable.Pais];
    };
    
    
    function noFilter(filterObj) {
        for (var key in filterObj) {
            if (filterObj[key]) {
                return false;
            }
        }
        return true;
    }

});

angular.module('sampleapp').filter('pagination', function() {
	return function(input, start) {
		start = +start;
		return input.slice(start);
	}; 
			
});


