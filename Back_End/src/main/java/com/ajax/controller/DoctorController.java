package com.ajax.controller;

import java.util.HashMap;
import java.util.List;

import com.ajax.model.Doctor;
import com.ajax.services.DoctorService;

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
 * DoctorController
 */
@Controller
@CrossOrigin
@RequestMapping("doctor")
public class DoctorController {

    @Autowired
    DoctorService daoDoctor;

    /*@GetMapping(value = "index")
    public String listaDoctores() {
        return new String("views/Doctor/doctorIndex");
    }*/

    @GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Doctor> getAllDoctor() {
        return (List<Doctor>) daoDoctor.getAll();
    }

    // GUARDAR
    /*
     * @GetMapping(value = "save")
     * 
     * @ResponseBody public Boolean save(@RequestParam String nombre,
     * 
     * @RequestParam String direccion) { Doctor doctor = new Doctor();
     * 
     * doctor.setNombre(nombre); doctor.setDireccion(direccion);
     * 
     * try { rDoctor.save(doctor); return true; } catch (Exception e) { return
     * false; } }
     */

    // GUARDAR
    @GetMapping(value = "save")
    @ResponseBody
    public HashMap<String, String> save(@RequestParam String nombre, @RequestParam String direccion,
            @RequestParam Integer idEspecialidad) {

        Doctor doctor = new Doctor();
        HashMap<String, String> jsonReturn = new HashMap<>();

        doctor.setNombre(nombre);
        doctor.setDireccion(direccion);
        doctor.setEspecialidad(daoDoctor.getEspecialidad(idEspecialidad));

        try {
            daoDoctor.saveOrUpdate(doctor);

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
            Doctor doctor = daoDoctor.getDoctor(id);
            daoDoctor.delete(doctor);

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
    @GetMapping(value="update/{id}")
    @ResponseBody
    @CrossOrigin
    public HashMap<String, String> save(@RequestParam Integer id, 
                                        @RequestParam String nombre, 
                                        @RequestParam String direccion,
                                        @RequestParam Integer idEspecialidad) {

        Doctor doctor = new Doctor();
        HashMap<String, String> jsonReturn = new HashMap<>();

        doctor.setId(id);
        doctor.setNombre(nombre);
        doctor.setDireccion(direccion);
        doctor.setEspecialidad(daoDoctor.getEspecialidad(idEspecialidad));

        try {
            daoDoctor.saveOrUpdate(doctor);

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
    public Doctor getDoctor(@PathVariable Integer id) {
        return daoDoctor.getDoctor(id);
    }
}