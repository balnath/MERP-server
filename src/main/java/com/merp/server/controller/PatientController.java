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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
            return new ResponseEntity<String>("Patient registration successfull", HttpStatus.OK);
        } catch (Exception exception) {
            logger.error("Error occured while adding Patient", exception);
            return new ResponseEntity<String>("Error occured while adding Patient",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping("/get")
//    public ResponseEntity<String> getPatient() {
//        return new ResponseEntity<>("Some message", HttpStatus.OK);
//    }
    
}
