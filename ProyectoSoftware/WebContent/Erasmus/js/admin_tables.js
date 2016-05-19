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

var app = angular.module('myApp', []);
app.controller('adminTablesControl', function($scope, $http, $location) {
  $http.get("/ProyectoSoftware/AdminTables")
    .then(function(response) {
      $scope.myData = response.data.destino;
      $scope.myData2 = response.data.carrera;
      $scope.myData3 = response.data.admin;
      $scope.myData4 = response.data.asignatura;
    });
});
