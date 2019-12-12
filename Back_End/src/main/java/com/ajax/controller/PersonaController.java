package com.ajax.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ajax.model.Persona;
import com.ajax.model.TipoPersona;
import com.ajax.repository.IPersonaRepository;
import com.ajax.repository.ITipoPersonaRepository;

import org.springframework.web.bind.annotation.PostMapping;

/**
 * PersonaController
 */
@Controller
@RequestMapping("persona")
public class PersonaController {

    //Agregando referencia a capa de datos
    @Autowired
    IPersonaRepository rPersona;
    
    @Autowired
    ITipoPersonaRepository rTipo;

    /**
     * 
     * Metodo para cargar vista 
     * @return String
     */
    @GetMapping(value="index")
    public String index() {
        return new String("Persona");
    }
    
    /**
     * Metodo para retornar el listado de registros
     * en un formato JSON segun la ruta establecida
     * @return List<Persona>
     */
    @GetMapping(value="all",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Persona> getAll() {
        return (List<Persona>) rPersona.findAll();
    }
    
    /**
     * Metodo para retornar el listado de registros
     * en un formato JSON segun la ruta establecida
     * @return List<TipoPersona>
     */
    @GetMapping(value = "select", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<TipoPersona> getAllT() {
        return (List<TipoPersona>) rTipo.findAll();
    }

    //Metodo para editar o guardar un nuevo registro
    @PostMapping(value="saveOrUpdate",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean save(
        @RequestParam Integer id,
        @RequestParam String nombre,
        @RequestParam String direccion,
        @RequestParam String telefono,
            @RequestParam Integer tipoPersona) {

        Persona persona = null;

        if (id != null) {
            persona = new Persona(id, nombre, direccion, telefono, new TipoPersona(tipoPersona));
        } else if (id == null) {
            persona = new Persona(nombre, direccion, telefono, new TipoPersona(tipoPersona));
        }
        rPersona.save(persona);

        return true;
    }
    
    //Metodo para eliminar registro
    @GetMapping(value="delete/{id}")
    @ResponseBody
    public Boolean delete(@PathVariable Integer id) {
        Persona p = rPersona.findById(id).get();
        rPersona.delete(p);
        return true;
    }
    
    //Metodo para buscar registro por ID y cargarlo en modal editar
    @GetMapping(value="get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Persona getMethodName(@PathVariable Integer id) {
        return rPersona.findById(id).get();
    }

}