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

import com.ajax.model.TipoPersona;
import com.ajax.repository.ITipoPersonaRepository;

import org.springframework.web.bind.annotation.PostMapping;

/**
 * TipoPersonaController
 */
@Controller
@RequestMapping("tipoPersona")
public class TipoPersonaController {

    // Agregando referencia a capa de datos
    @Autowired
    ITipoPersonaRepository rTipo;

    /**
     * 
     * Metodo para cargar vista
     * 
     * @return String
     */
    @GetMapping(value = "index")
    public String index() {
        return new String("TipoPersona");
    }

    /**
     * Metodo para retornar el listado de registros en un formato JSON segun la ruta
     * establecida
     * 
     * @return List<TipoPersona>
     */
    @GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<TipoPersona> getAll() {
        return (List<TipoPersona>) rTipo.findAll();
    }

    /**
     * Metodo para retornar el listado de registros en un formato JSON segun la ruta
     * establecida
     * 
     * @return List<TipoPersona>
     */
    /*@GetMapping(value = "select", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<TipoPersona> getAllT() {
        return (List<TipoPersona>) rTipo.findAll();
    }*/

    // Metodo para editar o guardar un nuevo registro
    @PostMapping(value = "saveOrUpdate", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean save(@RequestParam Integer id, @RequestParam String tipo) {

        TipoPersona tp = null;

        if (id != null) {
            tp = new TipoPersona(id, tipo);
        } else if (id == null) {
            tp = new TipoPersona(tipo);
        }
        rTipo.save(tp);

        return true;
    }

    // Metodo para eliminar registro
    @GetMapping(value = "delete/{id}")
    @ResponseBody
    public Boolean delete(@PathVariable Integer id) {
        TipoPersona tp = rTipo.findById(id).get();
        rTipo.delete(tp);
        return true;
    }

    // Metodo para buscar registro por ID
    @GetMapping(value = "get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public TipoPersona getMethodName(@PathVariable Integer id) {
        return rTipo.findById(id).get();
    }

}