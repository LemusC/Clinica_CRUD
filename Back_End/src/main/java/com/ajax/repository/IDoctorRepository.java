package com.ajax.repository;

import com.ajax.model.Doctor;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * IDoctorRepository
 */
@Repository
public interface IDoctorRepository extends CrudRepository<Doctor, Integer> {

}