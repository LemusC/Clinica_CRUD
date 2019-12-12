package com.ajax.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * Persona
 */
@Entity
public class Persona {

    //PROPIEDADES DE LA CLASE ENTIDAD
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Integer id;

    @NotNull
    private String nombre;

    @NotNull
    private String direccion;

    @NotNull
    private String telefono;

    @ManyToOne(optional = true,fetch = FetchType.EAGER)
    private TipoPersona tipoPersona;

    //CONSTRUCTORES
   public Persona() {
       super();
   }
   
   public Persona( String nombre,String direccion, String telefono, TipoPersona tipoPersona) {
       this.nombre=nombre;
       this.direccion=direccion;
       this.telefono=telefono;
       this.tipoPersona=tipoPersona;
   }

   public Persona(Integer id, String nombre,String direccion, String telefono) {
       this.id=id;
       this.nombre=nombre;
       this.direccion=direccion;
       this.telefono=telefono;
   }
   public Persona(Integer id, String nombre,String direccion, String telefono, TipoPersona tipoPersona) {
       this.id=id;
       this.nombre=nombre;
       this.direccion=direccion;
       this.telefono=telefono;
       this.tipoPersona=tipoPersona;
   }

   //SETTER Y GETTER
    /**
     * @param tipoPersona the tipoPersona to set
     */
    public void setTipoPersona(TipoPersona tipoPersona) {
        this.tipoPersona = tipoPersona;
    }
    /**
     * @return the tipoPersona
     */
    public TipoPersona getTipoPersona() {
        return tipoPersona;
    }
   /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }
}