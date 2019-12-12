package com.ajax.controller;

import java.util.HashMap;
import java.util.List;

import com.ajax.model.Paciente;
import com.ajax.repository.IPacienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * PacienteController
 */
@Controller
@CrossOrigin
@RequestMapping("paciente")
public class PacienteController {

    @Autowired
    IPacienteRepository rPaciente;

    @GetMapping(value = "index")
    public String listaPacientes() {
        return new String("views/Paciente/pacienteIndex");
    }

    //LISTAR
    @GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Paciente> getAll() {
        return (List<Paciente>) rPaciente.findAll();
    }

    //GUARDAR
    @GetMapping(value = "save")
    @ResponseBody
    public HashMap<String, String> save(@RequestParam String nombre, @RequestParam String direccion) {

        Paciente paciente = new Paciente();
        HashMap<String, String> jsonReturn = new HashMap<>();

        paciente.setNombre(nombre);
        paciente.setDireccion(direccion);

        try {
            rPaciente.save(paciente);

            jsonReturn.put("Estado", "OK");
            jsonReturn.put("Mensaje", "Registro guardado");

            return jsonReturn;
        } catch (Exception e) {

            jsonReturn.put("Estado", "Error");
            jsonReturn.put("Mensaje", "Registro no guardado");

            return jsonReturn;
        }
    }
    
    //ELIMINAR
    @GetMapping(value = "delete/{id}")
    @ResponseBody
    public HashMap<String, String> delete(@PathVariable Integer id) {

        HashMap<String, String> jsonReturn = new HashMap<>();

        try {
            Paciente paciente = rPaciente.findById(id).get();
            rPaciente.delete(paciente);

            jsonReturn.put("Estado", "OK");
            jsonReturn.put("Mensaje", "Registro eliminado");

            return jsonReturn;
        } catch (Exception e) {

            jsonReturn.put("Estado", "Error");
            jsonReturn.put("Mensaje", "Registro no eliminado");

            return jsonReturn;
        }
    }
    
    //ACTUALIZAR
    @GetMapping(value = "update/{id}")
    public HashMap<String, String> update(@RequestParam Integer id, @RequestParam String nombre,
            @RequestParam String direccion) {

        HashMap<String, String> jsonReturn = new HashMap<>();
        Paciente paciente = new Paciente();

        paciente.setId(id);
        paciente.setNombre(nombre);
        paciente.setDireccion(direccion);

        try {
            rPaciente.save(paciente);

            jsonReturn.put("Estado", "OK");
            jsonReturn.put("Mensaje", "Registro actualizado");

            return jsonReturn;
        } catch (Exception e) {

            jsonReturn.put("Estado", "Error");
            jsonReturn.put("Mensaje", "Registro no actualizado");

            return jsonReturn;
        }
    }
    
    @GetMapping(value = "get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Paciente getPaciente(@PathVariable Integer id) {
        return rPaciente.findById(id).get();
    }
    
}