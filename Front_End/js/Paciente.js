let paciente = {
    id:0
}

function setIdPaciente(id) {
    paciente.id = id
}

$(document).ready(inicio);

//FUNCION INICIO
function inicio() {
    cargarDatos();
    $("#btnGuardar").click(guardar);
    $("#btnEliminar").click(function () {
        eliminar(paciente.id)
    });
    $("#btnActualizar").click(modificar);
    $("#btnCancelar").click(reset);
}

function reset() {
    $("#nombre").val(null);
    $("#direccion").val(null);

    $("#nombre2").val(null);
    $("#direccion2").val(null);
}

//CARGANDO DATOS A TABLA ESPECIALIDADES
function cargarDatos() {
    $.ajax({
        url: "http://localhost:8080/paciente/all",
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
                    "<td>" +
                    "<button onclick='cargarRegistro(" + response[i].id +
                    ")'type='button' class='btn btn-warning ml-3 mt-1' data-toggle='modal' data-target='#editar'><i class='fas fa-edit'></i> <strong>Editar</strong></button>" +
                    "<button onclick='setIdPaciente(" + response[i].id +
                    ");' type='button' class='btn btn-danger ml-3 mt-1' style='color: black' data-toggle='modal' data-target='#eliminar'><i class='fas fa-trash-alt'></i> <strong>Eliminar</strong></button>" +
                    "</td>" +
                    "</tr>"
                )
            }
        },
        error: function (response) {
            alert("Error: " + response);
        }
    });
}

function guardar(response) {
    $.ajax({
        url: "http://localhost:8080/paciente/save",
        method: "Get",
        data: {
            nombre: $("#nombre").val(),
            direccion: $("#direccion").val()
        },
        success: function () {
            cargarDatos();
            reset();
        },
        error: function (response) {
            alert("Error en la peticion: " + response)
        }
    })
}

function eliminar(id) {
    $.ajax({
        url: "http://localhost:8080/paciente/delete/" + id,
        method: "Get",
        success: function () {
            cargarDatos();
        },
        error: function (response) {
            alert("Error en la peticion: " + response)
        }
    })
}

function cargarRegistro(id) {
    $.ajax({
        url: "http://localhost:8080/paciente/get/" + id,
        method: "Get",
        success: function (response) {
            $("#id2").val(response.id)
            $("#nombre2").val(response.nombre)
            $("#direccion2").val(response.direccion)
        },
        error: function (response) {
            alert("Error en la peticion " + response);
        }
    })
}

function modificar() {
    var id = $("#id2").val();
    $.ajax({
        url: "http://localhost:8080/paciente/update/" + id,
        method: "Get",
        data: {
            id: id,
            nombre: $("#nombre2").val(),
            direccion: $("#direccion2").val()
        },
        success: function (response) {
            cargarDatos();
            reset();
        },
        error: function (response) {
            alert("Error en la peticion: " + response)
        }
    });
}