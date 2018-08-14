package com.merp.server.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.merp.server.model.Patient;
import com.merp.server.service.PatientService;

@RestController
@RequestMapping("/patient")
public class PatientController {
	@Autowired
	PatientService patientService;

	
//	@RequestMapping(value= "/create", method= RequestMethod.POST)
//	@ResponseBody
	@PostMapping("/create")
	public ResponseEntity<String> savePatientInfo(@Valid @RequestBody Patient patient) {
		System.out.println("In controller");
		patientService.registerPatient(patient);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@GetMapping("/get")
	public Patient getPatient(){
		System.out.println("I am in controller");
		return null;
	}
}
