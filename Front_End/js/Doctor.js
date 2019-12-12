let doctor = {
    id: 0
};

function setIdDoctor(id) {
    doctor.id = id;
}

$(document).ready(inicio);

//FUNCION INICIO
function inicio() {
    cargarDatos();
    cargarSelect();
    cargarSelect2();
    $("#btnGuardar").click(guardar);
    $("#btnEliminar").click(function () {
        eliminar(doctor.id);
    });
    $("#btnActualizar").click(modificar);
    $("#btnCancelar").click(reset);
}

function reset() {
    $("#id").val(null);
    $("#nombre").val(null);
    $("#direccion").val("");
    $("#direccion").text("");
    $("#especialidad").val(null);

    $("#id2").val(null);
    $("#nombre2").val(null);
    $("#direccion2").val("");
    $("#direccion2").text("");
    $("#especialidad2").val(null);
}

//CARGANDO DATOS A TABLA ESPECIALIDADES
function cargarDatos() {
    $.ajax({
        url: "http://localhost:8080/doctor/all",
        method: "Get",
        data: null,
        success: function (response) {
            $("#datos").html("");

            for (let i = 0; i < response.length; i++) {
                $("#datos").append(
                    "<tr>" +
                    "<td><strong>" + response[i].id + "</strong></td>" +
                    "<td><strong>" + response[i].nombre + "</strong></td>" +
                    "<td><strong>" + response[i].direccion + "</strong></td>" +
                    "<td><strong>" + response[i].especialidad.especialidad + "</strong></td>" +
                    "<td>" +
                    "<button onclick='cargarRegistro(" + response[i].id +
                    ")'type='button' class='btn btn-warning ml-3 mt-1' data-toggle='modal' data-target='#editar'><i class='fas fa-edit'></i> <strong>Editar</strong></button>" +
                    "<button onclick='setIdDoctor(" + response[i].id +
                    ");' type='button' class='btn btn-danger ml-3 mt-1' style='color: black' data-toggle='modal' data-target='#eliminar'><i class='fas fa-trash-alt'></i> <strong>Eliminar</strong></button>" +
                    "</td>" +
                    "</tr>"
                )
            }
        },
        error: function () {
            alert("Error");
        }
    });
};

//Funcion para cargar select de modal nuevo
function cargarSelect() {
    $.ajax({
        url: "http://localhost:8080/especialidad/all",
        method: "Get",
        success: function (response) {

            var $select = $('#tSelect');

            $.each(response, function (i) {
                $select.append('<option value=' + response[i].id + '>' + response[i].especialidad +
                    '</option>');
            });
        },
        error: function (response) {
            alert("Eror en la peticion: " + response);
        }
    });
}

//Funcion para cargar select de modal editar
function cargarSelect2() {
    $.ajax({
        url: "http://localhost:8080/especialidad/all",
        method: "Get",
        success: function (response) {

            var $select = $('#tSelect2');

            $.each(response, function (i) {
                $select.append('<option value=' + response[i].id + '>' + response[i].especialidad +
                    '</option>');
            });
        },
    });
}

function guardar() {
    $.ajax({
        url: "http://localhost:8080/doctor/save",
        method: "Get",
        data: {
            nombre: $("#nombre").val(),
            direccion: $("#direccion").val(),
            idEspecialidad: $("#tSelect").val()
        },
        success: function (response) {
            reset();
            cargarDatos();
        },
        error: function (response) {
            alert("Error en la peticion " + response);
        }
    })
}

function eliminar(id) {
    $.ajax({
        url: "http://localhost:8080/doctor/delete/" + id,
        method: "Get",
        success: function (response) {
            reset();
            cargarDatos();
        },
        error: function (response) {
            alert("Error en la peticion " + response);
        }
    })
}

function cargarRegistro(id) {
    $.ajax({
        url: "http://localhost:8080/doctor/get/" + id,
        method: "Get",
        success: function (response) {
            $("#id2").val(response.id);
            $("#nombre2").val(response.nombre);
            $("#direccion2").val(response.direccion);
            $("#tSelect2").val(response.especialidad.id);
        },
        error: function (response) {
            alert("Error en la peticion " + response);
        }
    })
}

function modificar() {
    var id = $("#id2").val();
    $.ajax({
        url: "http://localhost:8080/doctor/update/"+id,
        method: "Get",
        data: {
            id: id,
            nombre: $("#nombre2").val(),
            direccion: $("#direccion2").val(),
            idEspecialidad: $("#tSelect2").val()
        },
        success: function (response) {
            cargarDatos();
            reset();
        },
        error: function (response) {
            alert("Error en la peticion " + response);
        }
    }); 
}