package com.ajax.repository;

import com.ajax.model.Paciente;

import org.springframework.data.repository.CrudRepository;

/**
 * IPacienteRepository
 */
public interface IPacienteRepository extends CrudRepository<Paciente, Integer>{

}