package com.merp.server.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.merp.server.model.Patient;
@Repository
public interface PatientRepository extends CrudRepository<Patient, Long> {
    
    public Patient findByEmailId(String emailId);
}
