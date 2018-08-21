package com.merp.server.repository;

import org.springframework.stereotype.Repository;

import com.merp.server.model.Patient;
import org.springframework.data.repository.PagingAndSortingRepository;
@Repository
public interface PatientRepository extends PagingAndSortingRepository<Patient, Long> {
    
    public Patient findByEmailId(String emailId);
}
