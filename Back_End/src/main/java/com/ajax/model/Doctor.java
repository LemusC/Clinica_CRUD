package com.ajax.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Doctor
 */
@Entity
@Table(name = "doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String nombre;

    @NotNull
    private String direccion;

    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull
    private Especialidad especialidad;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Consulta> consulta;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Paciente> paciente;

    public Doctor() {

    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Especialidad getEspecialidad() {
        return this.especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

}