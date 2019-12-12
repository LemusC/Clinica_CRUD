package com.ajax.controller;

import java.util.HashMap;
import java.util.List;

import com.ajax.model.Especialidad;
import com.ajax.repository.IEspecialidadRepository;

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
 * EspecialidadController
 */
@Controller
@CrossOrigin
@RequestMapping("especialidad")
public class EspecialidadController {

    @Autowired
    IEspecialidadRepository rEspecialidad;

    @GetMapping(value = "index")
    public String listaEspecialidades() {
        return new String("views/Especialidad/especialidadIndex");
    }
    
    @GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Especialidad> getAllDoctor() {
        return (List<Especialidad>) rEspecialidad.findAll();
    }

    // GUARDAR
    @GetMapping(value = "save")
    @ResponseBody
    public HashMap<String, String> save(@RequestParam String especialidad) {

        Especialidad es = new Especialidad();
        HashMap<String, String> jsonReturn = new HashMap<>();

        es.setEspecialidad(especialidad);

        try {
            rEspecialidad.save(es);

            jsonReturn.put("Estado", "OK");
            jsonReturn.put("Mensaje", "Registro guardado");

            return jsonReturn;
        } catch (Exception e) {

            jsonReturn.put("Estado", "Error");
            jsonReturn.put("Mensaje", "Registro no guardado" + e.getMessage());

            return jsonReturn;
        }
    }

    // ELIMINAR
    @GetMapping(value = "delete/{id}")
    @ResponseBody
    public HashMap<String, String> delete(@PathVariable Integer id) {

        HashMap<String, String> jsonReturn = new HashMap<>();

        try {
            Especialidad es = rEspecialidad.findById(id).get();
            rEspecialidad.delete(es);

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
    public HashMap<String, String> save(@RequestParam Integer id, @RequestParam String especialidad) {

        Especialidad es = new Especialidad();
        HashMap<String, String> jsonReturn = new HashMap<>();

        es.setId(id);
        es.setEspecialidad(especialidad);

        try {
            rEspecialidad.save(es);

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
    public Especialidad getEspecialidad(@PathVariable Integer id) {
        return rEspecialidad.findById(id).get();
    }
}