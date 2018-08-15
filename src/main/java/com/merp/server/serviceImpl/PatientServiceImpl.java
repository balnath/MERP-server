package com.merp.server.serviceImpl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.merp.server.model.Patient;
import com.merp.server.repository.PatientRepository;
import com.merp.server.service.PatientService;
import javax.inject.Inject;

@Service
@Transactional
public class PatientServiceImpl implements PatientService {
	@Inject
	PatientRepository patienRepository;

	@Override
	public void registerPatient(Patient patient) {
		patienRepository.save(patient);

	}

}
