
package com.ajax.repository;

import com.ajax.model.Persona;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * IPersonaRepository
 */
@Repository
public interface IPersonaRepository extends CrudRepository<Persona,Integer>{

    
}