$(document).ready(inicio)

function inicio() {
    $("#buscarDoctor").click(cargarDoctores);
    $("#buscarPaciente").click(cargarPacientes);
    $("#agregarSintoma").click(agregarDetalle);
    resetDetalles();

    $("#doctores").dataTable({
        ajax:{
            url: "http://localhost:8080/doctor/all",
                method: "Get"
        },
        columns: [
            {
                data: "id",
                width: "20%"
            },
            {
                data: "nombre",
                width: "20%"
            },
            {
                data: "direccion",
                width: "20%"
            },
            {
                data: "id",
                width: "20%"
            }
        ]
        
    })
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

function cargarDoctores() {
    $.ajax({
        url: "http://localhost:8080/doctor/all",
        method: "Get",
        data: null,
        success: function (response) {
            $("#datosDoctor").html("");

            response.forEach(i => {
                $("#datosDoctor").append("<tr>" +
                    "<td><strong>" + i.id + "</strong></td>" +
                    "<td><strong>" + i.nombre + "</strong></td>" +
                    "<td><strong>" + i.direccion + "</strong></td>" +
                    "<td><strong>" + i.especialidad.especialidad + "</strong></td>" +
                    "<td><button  class='btn btn-success' data-dismiss='modal'><i style='color: black'class = 'fas fa-plus'></i><strong style='color: black'>Agregar</strong></button></td > " +
                    "</tr>")
            })
        },
        error: function () {
            alert("Error");
        }
    });
};

function cargarPacientes() {
    $.ajax({
        url: "http://localhost:8080/paciente/all",
        method: "Get",
        data: null,
        success: function (response) {
            $("#datosPaciente").html("");

            response.forEach(i => {
                $("#datosPaciente").append("<tr>" +
                    "<td><strong>" + i.id + "</strong></td>" +
                    "<td><strong>" + i.nombre + "</strong></td>" +
                    "<td><strong>" + i.direccion + "</strong></td>" +
                    "<td><button  class='btn btn-success' data-dismiss='modal'><i style='color: black'class ='fas fa-plus'></i><strong style='color: black'>Agregar</strong></button></td>" +
                    "</tr>")
            })
        },
        error: function () {
            alert("Error");
        }
    });
};