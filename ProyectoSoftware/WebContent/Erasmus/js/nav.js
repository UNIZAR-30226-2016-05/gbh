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
app.controller('navCtrl', function($scope, $location) {
  // Cookies de usuario
  $scope.userMail = getCookie('userMail');
  $scope.adminVal = getCookie('admin');
});
