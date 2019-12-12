let especialidad = {
    id: 0
}

function setIdEspecialidad(id) {
    especialidad.id = id;
}

$(document).ready(inicio);

//FUNCION INICIO
function inicio() {
    cargarDatos();
    $("#btnGuardar").click(guardar);
    $("#btnEliminar").click(function () {
        eliminar(especialidad.id);
    });
    $("#btnActualizar").click(modificar);
    $("#btnCancelar").click(reset);
}

function reset() {
    $("#especialidad").val(null);

    $("#especialidad2").val(null);
}

//CARGANDO DATOS A TABLA ESPECIALIDADES
function cargarDatos() {
    $.ajax({
        url: "http://localhost:8080/especialidad/all",
        method: "Get",
        data: null,
        success: function (response) {
            $("#datos").html("");

            for (let i = 0; i < response.length; i++) {
                $("#datos").append(
                    "<tr>" +
                    "<td><strong>" + response[i].id + "</strong></td>" +
                    "<td><strong>" + response[i].especialidad + "</strong></td>" +
                    "<td>" +
                    "<button onclick='cargarRegistro(" + response[i].id +
                    ")'type='button' class='btn btn-warning ml-3 mt-1' data-toggle='modal' data-target='#editar'><i class='fas fa-edit'></i> <strong>Editar</strong></button>" +
                    "<button onclick='setIdEspecialidad(" + response[i].id +
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

function guardar() {
    $.ajax({
        url: "http://localhost:8080/especialidad/save",
        method: "Get",
        data: {
            especialidad: $("#especialidad").val(),
        },
        success: function () {
            reset();
            cargarDatos();
        },
        error: function () {
            alert("Eror en la peticion: " + response);
        }
    })
}

function eliminar(id) {
    $.ajax({
        url: "http://localhost:8080/especialidad/delete/" + id,
        method: "Get",
        success: function () {
            reset();
            cargarDatos();
        },
        error: function () {
            alert("Error en la peticion " + response);
        }
    })
}

function cargarRegistro(id) {
    $.ajax({
        url: "http://localhost:8080/especialidad/get/" + id,
        method: "Get",
        success: function (response) {
            $("#id2").val(response.id)
            $("#especialidad2").val(response.especialidad)
        },
        error: function (response) {
            alert("Error en la peticion " + response);
        }
    })
}

function modificar() {
    var id = $("#id2").val();
    console.log($("#especialidad2").val())
    $.ajax({
        url: "http://localhost:8080/especialidad/update/"+id,
        method: "Get",
        data: {
            id: id,
            especialidad: $("#especialidad2").val()
        },
        success: function (response) {
            cargarDatos();
            reset(); 
        },
        error: function (response) {
            alert("Error en la peticion: " + response)
        }
    })

}