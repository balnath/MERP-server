package com.merp.server.serviceImpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.merp.server.model.Patient;
import com.merp.server.repository.PatientRepository;
import com.merp.server.service.PatientService;

@Service
@Transactional
public class PatientServiceImpl implements PatientService {
	@Autowired
	PatientRepository patienRepository;

	@Override
	public void registerPatient(Patient patient) {
		patienRepository.save(patient);

	}

}
