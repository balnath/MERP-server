package com.merp.server.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.merp.server.model.Patient;
import com.merp.server.service.PatientService;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    PatientService patientService;

    private static final Logger logger = LogManager.getLogger();

    @PostMapping("/add-patient")
    public ResponseEntity<String> savePatientInfo(@Valid @RequestBody Patient patient) {
        try {
            patientService.registerPatient(patient);
            return new ResponseEntity<>("Patient registration successfull", HttpStatus.OK);
        } catch (Exception exception) {
            logger.error("Error occured while adding Patient", exception);
            return new ResponseEntity<>("Error occured while adding Patient", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-all-patients")
    public ResponseEntity<List<Patient>> getAllPatients() {
        try {
            List<Patient> patients = patientService.getAllPatients();
            return new ResponseEntity<>(patients, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error occured while fetching Patient", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-all-patients-by-receptionist-id")
    public ResponseEntity<List<Patient>> getPatientsByReceptionist(@RequestParam long receptionistId) {
        try {
            List<Patient> patients = patientService.getPatientsByReceptionist(receptionistId);
            return new ResponseEntity<>(patients, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error occured while fetching Patient by receptionist id : " + receptionistId, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/get-all-patients-by-assigned-doctor-id")
    public ResponseEntity<List<Patient>> getPatientsByAssignedDoctor(@RequestParam long assignedDoctorId) {
        try {
            List<Patient> patients = patientService.getPatientsByAssignedDoctor(assignedDoctorId);
            return new ResponseEntity<>(patients, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error occured while fetching Patient by doctor id : " + assignedDoctorId, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/get-patient-by-id")
    public ResponseEntity<Patient> getPatientsById(@RequestParam long id) {
        try {
            Patient patient = patientService.getPatientById(id);
            return new ResponseEntity<>(patient, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error occured while fetching Patient with id : " + id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
