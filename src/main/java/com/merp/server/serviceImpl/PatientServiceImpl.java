package com.merp.server.serviceImpl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.merp.server.model.Patient;
import com.merp.server.repository.PatientRepository;
import com.merp.server.service.PatientService;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
@Transactional
public class PatientServiceImpl implements PatientService {

    @Inject
    PatientRepository patienRepository;
    
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void registerPatient(Patient patient) throws Exception {
        try {
            patienRepository.save(patient);
            Calendar calendar = Calendar.getInstance();
            String dayValue = String.format("%02d", calendar.get(Calendar.DAY_OF_MONTH));
            String monthValue = String.format("%02d", calendar.get(Calendar.MONTH)+1);
            String yearValue = Integer.toString(calendar.get(Calendar.YEAR));
            String registrationNo = "HSP-" + yearValue + monthValue + dayValue + "-" + patient.getId();
            patient.setRegistrationNo(registrationNo);
            patienRepository.save(patient);
        } catch (Exception e) {
            logger.error("Error occured while adding patient", e);
            throw new Exception("Error occured while adding patient");
        }

    }

    @Override
    public List<Patient> getAllPatients() throws Exception {
        try {
            return patienRepository.getAllPatients();
        } catch (Exception e) {
            logger.error("Error occured while fetching all patients", e);
            throw new Exception("Error occured while fetching all patients");
        }
    }

    @Override
    public List<Patient> getPatientsByReceptionist(long receptionistId) throws Exception {
        try {
            return patienRepository.getPatientsByReceptionist(receptionistId);
        } catch (Exception e) {
            logger.error("Error occured while fetching all patients having receptionist with id : "  + receptionistId, e);
            throw new Exception("Error occured while fetching all patients having receptionist with id : "  + receptionistId);
        }
    }

    @Override
    public List<Patient> getPatientsByAssignedDoctor(long assignedDoctorId) throws Exception {
        try {
            return patienRepository.getPatientsByAssignedDoctor(assignedDoctorId);
        } catch (Exception e) {
            logger.error("Error occured while fetching all patients having doctor with id : "  + assignedDoctorId, e);
            throw new Exception("Error occured while fetching all patients having doctor with id : "  + assignedDoctorId);
        }
    }

    @Override
    public Patient getPatientById(long id) throws Exception {
        try {
            return patienRepository.getPatientById(id);
        } catch (Exception e) {
            logger.error("Error occured while fetching patient with id : "  + id, e);
            throw new Exception("Error occured while fetching patient with id : "  + id);
        }
    }
    
    
}
