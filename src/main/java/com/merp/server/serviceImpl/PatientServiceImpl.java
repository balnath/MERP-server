package com.merp.server.serviceImpl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.merp.server.model.Patient;
import com.merp.server.repository.PatientRepository;
import com.merp.server.service.PatientService;
import java.util.Calendar;
import java.util.Date;
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

}
