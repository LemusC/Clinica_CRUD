package com.ajax.controller;

import java.util.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.ajax.model.Consulta;
import com.ajax.model.DetallesConsulta;
import com.ajax.model.Doctor;
import com.ajax.model.Paciente;
import com.ajax.repository.IDoctorRepository;
import com.ajax.repository.IPacienteRepository;
import com.ajax.services.ConsultaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ConsultaController
 */
@Controller
@CrossOrigin
@RequestMapping(value = "consulta")
public class ConsultaController {

    @Autowired
    ConsultaService daoConsulta;

    @Autowired
    IPacienteRepository rPaciente;

    @Autowired
    IDoctorRepository rDoctor;

    public static List<DetallesConsulta> detalles = new ArrayList<>();

    public ConsultaController() {
        detalles = new ArrayList<>();
    }

    @GetMapping(value = "getDoctores", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @CrossOrigin
    public Object getDoctores() {
        List<HashMap<String, Object>> registros = new ArrayList<>();

        List<Doctor> l = (List<Doctor>) rDoctor.findAll();

        for (Doctor doctor : l) {
            HashMap<String, Object> object = new HashMap<>();

            object.put("id", doctor.getId());
            object.put("nombre", doctor.getNombre());
            object.put("direccion", doctor.getDireccion());
            object.put("especialidad", doctor.getEspecialidad().getEspecialidad());
            object.put("operacion",
                    "<button type='button' class='btn btn-primary agregarDoctor' data-dismiss='modal'><i style='color: black' class='fas fa-plus'></i><strong style='color: black'>Agregar</strong></button>");

            registros.add(object);
        }

        return Collections.singletonMap("data", registros);
    }

    @GetMapping(value = "getPacientes", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @CrossOrigin
    public Object getPacientes() {
        List<HashMap<String, Object>> registros = new ArrayList<>();

        List<Paciente> l = (List<Paciente>) rPaciente.findAll();

        for (Paciente paciente : l) {
            HashMap<String, Object> object = new HashMap<>();

            object.put("id", paciente.getId());
            object.put("nombre", paciente.getNombre());
            object.put("direccion", paciente.getDireccion());
            object.put("operacion",
                    "<button type='button' class='btn btn-primary agregarPaciente' data-dismiss='modal'><i style='color: black' class='fas fa-plus'></i><strong style='color: black'>Agregar</strong></button>");

            registros.add(object);
        }

        return Collections.singletonMap("data", registros);
    }

    @PostMapping(value = "agregarDetalle", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object agregarDetalle(@RequestParam String sintoma) {
        // CREANDO OBJETO DE DETALLE
        DetallesConsulta entity = new DetallesConsulta();
        HashMap<String, String> json = new HashMap<String, String>();

        entity.setSintoma(sintoma);

        // AGREGANDO OBJETO DETALLE A LA LISTA

        try {
            detalles.add(entity);
            json.put("Mensaje", "Detalle agregado");
            json.put("Status", "200");

            return json;
        } catch (Exception e) {
            json.put("Mensaje", "Error");
            json.put("Status", "404");

            return json;
        }

    }

    @GetMapping(value = "detalles", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object getDetalles() {
        return detalles;
    }

    @PostMapping(value = "resetDetalles", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object resetDetalles() {
        detalles = new ArrayList<>();
        return "lista reseteada";
    }

    /*
     * @GetMapping(value = "index") public String listaConsultas() { return new
     * String("views/Consulta/consultaIndex"); }
     */

    @GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Consulta> getAllDoctor() {
        return (List<Consulta>) daoConsulta.getAll();
    }

    // GUARDAR
    /*
     * @GetMapping(value = "save")
     * 
     * @ResponseBody public HashMap<String, String> save(@RequestParam Date
     * fecha, @RequestParam String diagnostico,
     * 
     * @RequestParam Integer idDoctor, @RequestParam Integer idPaciente) {
     * 
     * Consulta consulta = new Consulta(); HashMap<String, String> jsonReturn = new
     * HashMap<>();
     * 
     * consulta.setFecha(fecha); consulta.setDiagnostico(diagnostico);
     * consulta.setDoctor(daoConsulta.getDoctor(idDoctor));
     * consulta.setPaciente(daoConsulta.getPaciente(idPaciente));
     * 
     * try { daoConsulta.saveOrUpdate(consulta);
     * 
     * jsonReturn.put("Estado", "OK"); jsonReturn.put("Mensaje",
     * "Registro guardado");
     * 
     * return jsonReturn; } catch (Exception e) {
     * 
     * jsonReturn.put("Estado", "Error"); jsonReturn.put("Mensaje",
     * "Registro no guardado" + e.getMessage());
     * 
     * return jsonReturn; } }
     */

    @PostMapping(value = "save")
    @ResponseBody
    @CrossOrigin
    public Boolean save(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha, @RequestParam String diagnostico,
            @RequestParam Integer idDoctor, @RequestParam Integer idPaciente) {

        Consulta entity = new Consulta();
        entity.setDoctor(daoConsulta.getDoctor(idDoctor));
        entity.setPaciente(daoConsulta.getPaciente(idPaciente));
        entity.setDiagnostico(diagnostico);
        entity.setFecha(fecha);

        for (DetallesConsulta detalleConsulta : detalles) {
            detalleConsulta.setConsulta(entity);
        }

        entity.setDetalles_Consultas(detalles);

        try {
            daoConsulta.save(entity);
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }

    }

    // ELIMINAR
    @GetMapping(value = "delete/{id}")
    @ResponseBody
    public HashMap<String, String> delete(@PathVariable Integer id) {

        HashMap<String, String> jsonReturn = new HashMap<>();

        try {
            Consulta consulta = daoConsulta.getConsulta(id);
            daoConsulta.delete(consulta);

            jsonReturn.put("Estado", "OK");
            jsonReturn.put("Mensaje", "Registro eliminado");

            return jsonReturn;
        } catch (Exception e) {

            jsonReturn.put("Estado", "Error");
            jsonReturn.put("Mensaje", "Registro no eliminado" + e.getMessage());

            return jsonReturn;
        }
    }

    // ACTUALIZAR
    @GetMapping(value = "update/{id}")
    @ResponseBody
    public HashMap<String, String> save(@RequestParam Integer id, @RequestParam Date fecha,
            @RequestParam String diagnostico, @RequestParam Integer idDoctor, @RequestParam Integer idPaciente) {

        Consulta consulta = new Consulta();
        HashMap<String, String> jsonReturn = new HashMap<>();

        consulta.setId(id);
        consulta.setFecha(fecha);
        consulta.setDiagnostico(diagnostico);
        consulta.setDoctor(daoConsulta.getDoctor(idDoctor));
        consulta.setPaciente(daoConsulta.getPaciente(idPaciente));

        try {
            daoConsulta.saveOrUpdate(consulta);

            jsonReturn.put("Estado", "OK");
            jsonReturn.put("Mensaje", "Registro actualizado");

            return jsonReturn;
        } catch (Exception e) {

            jsonReturn.put("Estado", "Error");
            jsonReturn.put("Mensaje", "Registro no actualizado" + e.getMessage());

            return jsonReturn;
        }
    }

    @GetMapping(value = "get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Consulta getConsulta(@PathVariable Integer id) {
        return daoConsulta.getConsulta(id);
    }
}