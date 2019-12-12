let consulta = {
    id:0
}

function setIdConsulta(id) {
    consulta.id = id
}
$(document).ready(inicio);

//FUNCION INICIO
function inicio() {
    cargarDatos();
    cargarSelectDoctor();
    cargarSelectPaciente();
    cargarSelectDoctor2();
    cargarSelectPaciente2();
    $("#btnGuardar").click(guardar);
    $("#btnEliminar").click(function () {
        eliminar(consulta.id);
    });
    $("#btnActualizar").click(modificar);
    $("#btnCancelar").click(reset);
}

function reset() {
    $("#id").val(null);
    $("#fecha").val(null);
    $("#sintomas").val("");
    $("#diagnostico").text("");

    $("#id").val(null);
    $("#fecha2").val(null);
    $("#sintomas2").val("");
    $("#diagnostico2").text("");
}

//CARGANDO DATOS A TABLA ESPECIALIDADES
function cargarDatos() {
    $.ajax({
        url: "http://localhost:8080/consulta/all",
        method: "Get",
        data: null,
        success: function (response) {
            $("#datos").html("");

            for (let i = 0; i < response.length; i++) {
                $("#datos").append(
                    "<tr>" +
                    "<td><strong>" + response[i].id + "</strong></td>" +
                    "<td><strong>" + response[i].paciente.nombre + "</strong></td>" +
                    "<td><strong>" + response[i].sintomas + "</strong></td>" +
                    "<td><strong>" + response[i].diagnostico + "</strong></td>" +
                    "<td><strong>" + response[i].doctor.nombre + "</strong></td>" +
                    "<td><strong>" + response[i].fecha + "</strong></td>" +
                    "<td>" +
                    "<button onclick='cargarRegistro(" + response[i].id +
                    ")'type='button' class='btn btn-warning ml-3 mt-1' data-toggle='modal' data-target='#editar'><i class='fas fa-edit'></i> <strong>Editar</strong></button>" +
                    "<button onclick='setIdConsulta(" + response[i].id +
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
function cargarSelectDoctor() {
    $.ajax({
        url: "http://localhost:8080/doctor/all",
        method: "Get",
        success: function (response) {

            var $select = $('#selectDoctor');

            $.each(response, function (i) {
                $select.append('<option value=' + response[i].id + '>' + response[i].nombre +
                    '</option>');
            });
        },
        error: function (response) {
            alert("Eror en la peticion: " + response);
        }
    });
}

//Funcion para cargar select de modal editar
function cargarSelectPaciente() {
    $.ajax({
        url: "http://localhost:8080/paciente/all",
        method: "Get",
        success: function (response) {

            var $select = $('#selectPaciente');

            $.each(response, function (i) {
                $select.append('<option value=' + response[i].id + '>' + response[i].nombre +
                    '</option>');
            });
        },
    });
}

//Funcion para cargar select de modal nuevo
function cargarSelectDoctor2() {
    $.ajax({
        url: "http://localhost:8080/doctor/all",
        method: "Get",
        success: function (response) {

            var $select = $('#selectDoctor2');

            $.each(response, function (i) {
                $select.append('<option value=' + response[i].id + '>' + response[i].nombre +
                    '</option>');
            });
        },
        error: function (response) {
            alert("Eror en la peticion: " + response);
        }
    });
}

//Funcion para cargar select de modal editar
function cargarSelectPaciente2() {
    $.ajax({
        url: "http://localhost:8080/paciente/all",
        method: "Get",
        success: function (response) {

            var $select = $('#selectPaciente2');

            $.each(response, function (i) {
                $select.append('<option value=' + response[i].id + '>' + response[i].nombre +
                    '</option>');
            });
        },
    });
}

function guardar() {
    $.ajax({
        url: "http://localhost:8080/consulta/save",
        method: "Get",
        data: {
            fecha: $("#fecha").val(),
            sintomas: $("#sintomas").val(),
            diagnostico: $("#diagnostico").val(),
            idDoctor: $("#selectDoctor").val(),
            idPaciente: $("#selectPaciente").val()
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
        url: "http://localhost:8080/consulta/delete/" + id,
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
        url: "http://localhost:8080/consulta/get/" + id,
        method: "Get",
        success: function (response) {
            $("#id2").val(response.id);
            $("#fecha2").val(response.fecha);
            $("#sintomas2").val(response.sintomas);
            $("#diagnostico2").val(response.diagnostico);
            $("#selectDoctor2").val(response.doctor.id);
            $("#selectPaciente2").val(response.paciente.id);
        },
        error: function (response) {
            alert("Error en la peticion " + response);
        }
    })
}

function modificar() {
    var id = $("#id2").val();
    $.ajax({
        url: "http://localhost:8080/consulta/update/" + id,
        method: "Get",
        data: {
            id: id,
            fecha: $("#fecha2").val(),
            sintomas: $("#sintomas2").val(),
            diagnostico: $("#diagnostico2").val(),
            idDoctor: $("#selectDoctor2").val(),
            idPaciente: $("#selectPaciente2").val()
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