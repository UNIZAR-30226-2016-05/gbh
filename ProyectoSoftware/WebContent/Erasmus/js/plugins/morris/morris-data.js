// Morris.js Charts sample data for SB Admin template

$(function() {

    // Area Chart
    Morris.Area({
        element: 'morris-area-chart',
        data: [{
            period: '2010 Q1',
            Destinos: 2666,
            Carreras: null,
            Asignaturas: 2647,
            Usuarios: 247,
            Comentarios: 26407,
            Valoraciones: 200
        }, {
            period: '2010 Q2',
            Destinos: 2778,
            Carreras: 2294,
            Asignaturas: 2441,
            Usuarios: 247,
            Comentarios: 26407,
            Valoraciones: 200
        }, {
            period: '2010 Q3',
            Destinos: 4912,
            Carreras: 1969,
            Asignaturas: 2501,
            Usuarios: 247,
            Comentarios: 26407,
            Valoraciones: 200
        }, {
            period: '2010 Q4',
            Destinos: 3767,
            Carreras: 3597,
            Asignaturas: 5689,
            Usuarios: 247,
            Comentarios: 26407,
            Valoraciones: 200
        }, {
            period: '2011 Q1',
            Destinos: 6810,
            Carreras: 1914,
            Asignaturas: 2293,
            Usuarios: 247,
            Comentarios: 26407,
            Valoraciones: 200
        }, {
            period: '2011 Q2',
            Destinos: 5670,
            Carreras: 4293,
            Asignaturas: 1881,
            Usuarios: 247,
            Comentarios: 26407,
            Valoraciones: 200
        }, {
            period: '2011 Q3',
            Destinos: 4820,
            Carreras: 3795,
            Asignaturas: 1588,
            Usuarios: 247,
            Comentarios: 6407,
            Valoraciones: 200
        }, {
            period: '2011 Q4',
            Destinos: 15073,
            Carreras: 5967,
            Asignaturas: 5175,
            Usuarios: 247,
            Comentarios: 26407,
            Valoraciones: 120
        }, {
            period: '2012 Q1',
            Destinos: 10687,
            Carreras: 4460,
            Asignaturas: 2028,
            Usuarios: 247,
            Comentarios: 26407,
            Valoraciones: 20
        }, {
            period: '2012 Q2',
            Destinos: 8432,
            Carreras: 5713,
            Asignaturas: 1791,
            Usuarios: 247,
            Comentarios: 26407,
            Valoraciones: 200
        }],
        xkey: 'period',
        ykeys: ['Destinos', 'Carreras', 'Asignaturas', 'Usuarios', 'Comentarios', 'Valoraciones'],
        labels: ['Destinos', 'Carreras', 'Asignaturas', 'Usuarios', 'Comentarios', 'Valoraciones'],
        pointSize: 2,
        hideHover: 'auto',
        resize: true
    });


});
