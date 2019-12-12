package com.ajax.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ajax.model.TipoPersona;

@Repository
public interface ITipoPersonaRepository extends CrudRepository<TipoPersona, Integer>{

}
