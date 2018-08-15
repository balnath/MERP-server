/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merp.server.service;

import com.merp.server.TestUtils;
import com.merp.server.model.Patient;
import com.merp.server.repository.PatientRepository;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Divyanshu
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PatientServiceTest {
    
    @Inject
    PatientService patientService;
    @Inject
    PatientRepository patientRepository;
    
    @Test
    public void registerPatient(){
        Patient patient = TestUtils.getPatient();
      
        patientService.registerPatient(patient);
        
        Patient foundPatient = patientRepository.findByEmailId(patient.getEmailId());
        Assert.assertTrue(foundPatient.getId() > 0);
    }
}
