
function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i=0; i<ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

function stop() {
    var i = 0;
}

function checkCookie() {
    var admin = getCookie("admin");
    if (admin == 0) {

      window.location = "/ProyectoSoftware/Erasmus/home.html"
      alert('Necesita iniciar sesión como administrador para acceder a este contenido.'
        + 'Será redirigido automáticamente.');

    }
}
