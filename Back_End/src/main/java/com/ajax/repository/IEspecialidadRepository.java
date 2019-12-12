package com.ajax.repository;

import com.ajax.model.Especialidad;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

/**
 * IEspecialidadRepository
 */
@Repository
public interface IEspecialidadRepository extends CrudRepository<Especialidad, Integer> {
    
}