package com.merp.server.repository;

import org.springframework.stereotype.Repository;

import com.merp.server.model.Patient;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
@Repository
public interface PatientRepository extends PagingAndSortingRepository<Patient, Long> {
    
    @Query(value = "Select p from Patient p where p.isActive = true")
    List<Patient> getAllPatients();
    
    @Query(value = "Select p from Patient p where p.emailId = :emailId and p.isActive = true")
    Patient findByEmailId(@Param("emailId") String emailId);
    
    @Query(value = "Select p from Patient p where p.receptionist.id = :receptionistId and p.isActive = true")
    List<Patient> getPatientsByReceptionist(@Param("receptionistId") long receptionistId);
    
    @Query(value = "Select p from Patient p where p.assignedDoctor.id = :assignedDoctorId and p.isActive = true")
    List<Patient> getPatientsByAssignedDoctor(@Param("assignedDoctorId") long assignedDoctorId);
    
    @Query(value = "Select p from Patient p where p.id = :id and p.isActive = true")
    Patient getPatientById(@Param("id") long id);
    
}
