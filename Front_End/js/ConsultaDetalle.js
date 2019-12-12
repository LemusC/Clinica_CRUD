let paciente = {
    id: 0,
    nombre: ""
};

let doctor = {
    id: 0,
    nombre: ""
};

$(document).ready(function () {

    resetDetalles();
    cargarPacientes();
    cargarDoctores();
    $("#agregarSintoma").click(agregarDetalle);

    $("body").on('click', '.agregarPaciente', function () {

        agregarPaciente($(this).parent().parent().children('td:eq(0)').text(), $(this).parent().parent().children('td:eq(1)').text());
    });

    $("body").on('click', '.agregarDoctor', function () {

        agregarDoctor($(this).parent().parent().children('td:eq(0)').text(), $(this).parent().parent().children('td:eq(1)').text());
    });
});

function cargarPacientes() {
    $("#pacientes").DataTable({

        "ajax": {
            "url": "http://localhost:8080/consulta/getPacientes",
            "method": "Get"
        },
        "columns": [{
                "data": "id",
                "width": "5%"
            },
            {
                "data": "nombre",
                "width": "30%"
            },
            {
                "data": "direccion",
                "width": "40%"
            },
            {
                "data": "operacion",
                "width": "10%"
            }
        ],
        "scrollY": 200,
        "language": {
            "lengthMenu": "Mostrar _MENU_ ",
            "zeroRecords": "Datos no encontrados",
            "info": "Mostar páginas _PAGE_ de _PAGES_",
            "infoEmpty": "Datos no encontrados",
            "infoFiltered": "(Filtrados por _MAX_ total registros)",
            "search": "Buscar:",
            "paginate": {
                "first": "Primero",
                "last": "Anterior",
                "next": "Siguiente",
                "previous": "Anterior"
            }
        }
    });
}

function cargarDoctores() {
    $("#doctores").DataTable({

        "ajax": {
            "url": "http://localhost:8080/consulta/getDoctores",
            "method": "Get"
        },
        "columns": [{
                "data": "id",
                "width": "5%"
            },
            {
                "data": "nombre",
                "width": "30%"
            },
            {
                "data": "direccion",
                "width": "30%"
            },
            {
                "data": "especialidad",
                "width": "30%"
            },
            {
                "data": "operacion",
                "width": "5%"
            }
        ],
        "scrollY": 200,
        "language": {
            "lengthMenu": "Mostrar _MENU_ ",
            "zeroRecords": "Datos no encontrados",
            "info": "Mostar páginas _PAGE_ de _PAGES_",
            "infoEmpty": "Datos no encontrados",
            "infoFiltered": "(Filtrados por _MAX_ total registros)",
            "search": "Buscar:",
            "paginate": {
                "first": "Primero",
                "last": "Anterior",
                "next": "Siguiente",
                "previous": "Anterior"
            }
        }
    });
}

function resetDetalles() {
    $.ajax({
        url: "http://localhost:8080/consulta/resetDetalles",
        method: "Post"
    });
}

function agregarDetalle() {
    $.ajax({
        url: "http://localhost:8080/consulta/agregarDetalle",
        method: "Post",
        data: {
            sintoma: $("#sintoma").val()
        },
        success: function (response) {
            console.log(response)
            cargarDetalles();
            $("#sintoma").val(null);
        },
        error: errorPeticion
    })
}

function cargarDetalles() {
    $.ajax({
        url: "http://localhost:8080/consulta/detalles",
        method: "Get",
        success: function (response) {
            $("#tDetalles").html(""); //reseteando datos

            //cargando datos en la tabla
            response.forEach(i => {
                console.log(i.sintoma)
                $("#tDetalles").append("" +
                    "<tr>" +
                    "<td>" + i.sintoma + "</td>" +
                    "<td><button class='btn btn-danger'>eliminar</button></td>" +
                    "</tr>");
            });
        },
        error: errorPeticion
    });
}

function errorPeticion(response) {
    console.log(response);
}

function agregarPaciente(id, nombre) {

    paciente.id = id;
    paciente.nombre = nombre;

    $("#paciente").val(nombre);
}

function agregarDoctor(id, nombre) {

    doctor.id = id;
    doctor.nombre = nombre;

    $("#doctor").val(nombre);
}