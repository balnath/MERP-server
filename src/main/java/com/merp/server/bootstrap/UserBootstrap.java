/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merp.server.bootstrap;

import com.merp.server.model.Department;
import com.merp.server.model.Role;
import com.merp.server.model.User;
import com.merp.server.repository.DepartmentRepository;
import com.merp.server.repository.RoleRepository;
import com.merp.server.repository.UserRepository;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Divyanshu
 */
@Component
public class UserBootstrap {

    @Inject
    UserRepository userRepository;
    @Inject
    RoleRepository roleRepository;
    @Inject
    DepartmentRepository departmentRepository;
    @Inject
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private static final Logger logger = LogManager.getLogger();

    @PostConstruct
    public void initializeAdminUser() {
        try {
            User user = userRepository.findByUsername("admin");
            if (user == null) {
                logger.info("Adding admin user to database !!!");
                user = new User();
                user.setUsername("admin");
                user.setPassword(bCryptPasswordEncoder.encode("password"));
                user.setName("Admin");
                Role adminRole = roleRepository.findByName("ADMIN");
                if (adminRole == null) {
                    adminRole = new Role();
                    adminRole.setName("ADMIN");
                    adminRole.setDescription("Description for ADMIN Role");
                    roleRepository.save(adminRole);
                }
                user.setRole(adminRole);
                String departmentName = "General";
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
                user.setDepartment(department);
                userRepository.save(user);
                logger.info("Admin user added successfully !");
            } else {
                logger.info("Admin user already added !!!");
            }
        } catch (Exception e) {
            logger.error("Exception occured while adding admin user !", e);
        }
    }
}
