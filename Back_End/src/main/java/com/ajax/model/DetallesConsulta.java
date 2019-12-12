package com.ajax.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * detallesConsulta
 */
@Entity
public class DetallesConsulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String sintoma;

    @ManyToOne(fetch = FetchType.EAGER)
    private Consulta consulta;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSintoma() {
        return this.sintoma;
    }

    public void setSintoma(String sintoma) {
        this.sintoma = sintoma;
    }

    public Consulta getConsulta() {
        return this.consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

}