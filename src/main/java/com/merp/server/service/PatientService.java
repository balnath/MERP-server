package com.merp.server.service;

import com.merp.server.model.Patient;
import java.util.List;

public interface PatientService {

    public void registerPatient(Patient patient) throws Exception;

    public List<Patient> getAllPatients() throws Exception;

    public List<Patient> getPatientsByReceptionist(long receptionistId) throws Exception;

    public List<Patient> getPatientsByAssignedDoctor(long assignedDoctorId) throws Exception;
    
    public Patient getPatientById(long id) throws Exception;

}
