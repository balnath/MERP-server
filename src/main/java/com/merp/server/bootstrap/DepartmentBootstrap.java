/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merp.server.bootstrap;

import com.merp.server.model.Department;
import com.merp.server.repository.DepartmentRepository;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 *
 * @author Divyanshu
 */
@Component
public class DepartmentBootstrap {

    @Inject
    DepartmentRepository departmentRepository;

    private static final Logger logger = LogManager.getLogger();

    @PostConstruct
    public void initializeDepartments() throws Exception {
        String departmentNames[] = {"General", "OPD", "Radiology", "Pharmacy", "ICU"};
        for (String departmentName : departmentNames) {
            try {

                Department department = departmentRepository.findByName(departmentName);
                if (department == null) {
                    logger.info("Adding Department : " + departmentName);
                    department = new Department();
                    department.setName(departmentName);
                    department.setDescription("Description for " + departmentName);
                    departmentRepository.save(department);
                    logger.info("Department " + departmentName + " added to the database");
                } else {
                    logger.info("Department " + departmentName + " already present in the database");
                }
            } catch (Exception e) {
                logger.error("Exception occured while adding Department for department name : " + departmentName, e);
            }
        }
    }
}
