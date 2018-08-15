/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merp.server;

import com.merp.server.model.Patient;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

/**
 *
 * @author Divyanshu
 */
public class TestUtils {

    public static String getRandomString(String input) {
        return input + UUID.randomUUID();
    }

    public static int getRandomInt(int start, int end) {
        Random random = new Random();
        return random.nextInt(end - start) + start;
    }

    public static Patient getPatient() {
        Patient patient = new Patient();
        patient.setFirstName(getRandomString("first name"));
        patient.setMiddleName(getRandomString("middle name"));
        patient.setLastName(getRandomString("last name"));
        patient.setEmailId(getRandomString("email id"));
        patient.setAge(getRandomInt(10, 90));
        patient.setMobileNo(getRandomString("mobile "));
        patient.setDob(Date.valueOf(LocalDate.now()));
        return patient;
    }
}
