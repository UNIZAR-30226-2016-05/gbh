// Devuelve el valor de un parametro
// en caso de no encontralo devuelve una cadena vacía
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

// Devuelve el valor de una cookie,
// en caso de no encotrarla devuevle una cadena vacía
function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i=0; i<ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length + 1, c.length - 1);
        }
    }
    return "";
}

var app = angular.module('myApp', []);
app.controller('destinoControl', function($scope, $http, $location) {
  var val = getQueryVariable("idDestino");
  $http.get("/ProyectoSoftware/SeleccionDestino?idDestino=" + val)
    .then(function(response) {
      $scope.myData = response.data.destino;
      $scope.myData2 = response.data.asignatura;
      $scope.myData3 = response.data.comentarios;
      $scope.destino = val;
      $scope.rating = response.data.Valoracion;
    });
  $scope.userMail = getCookie('userMail');
  $scope.adminVal = getCookie('admin');
  $scope.check = function(){
    document.getElementById('rating' + $scope.rating).checked = true;
  }
});
