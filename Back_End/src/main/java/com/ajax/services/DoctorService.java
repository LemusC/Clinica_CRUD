package com.ajax.services;

import java.util.List;

import com.ajax.model.Doctor;
import com.ajax.model.Especialidad;
import com.ajax.repository.IDoctorRepository;
import com.ajax.repository.IEspecialidadRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * DoctorService
 */
@Service
public class DoctorService {

    @Autowired
    IDoctorRepository rDoctor;

    @Autowired
    IEspecialidadRepository rEspecialidad;
    
    public List<Doctor> getAll() {
        return (List<Doctor>) rDoctor.findAll();
    }
    
    public Boolean saveOrUpdate(Doctor entity) {
        try {
            rDoctor.save(entity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public Boolean delete(Doctor entity) {
        try {
            rDoctor.delete(entity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
   
    public Especialidad getEspecialidad(Integer id) {
        return rEspecialidad.findById(id).get();
    }
    
    public Doctor getDoctor(Integer id) {
        return rDoctor.findById(id).get();
    }

}