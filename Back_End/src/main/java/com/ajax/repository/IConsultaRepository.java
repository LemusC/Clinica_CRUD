package com.ajax.repository;

import com.ajax.model.Consulta;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * IConsultaRepository
 */
@Repository
public interface IConsultaRepository extends CrudRepository<Consulta, Integer>{
    
}