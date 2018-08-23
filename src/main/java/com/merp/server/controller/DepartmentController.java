/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merp.server.controller;

import com.merp.server.model.Department;
import com.merp.server.service.DepartmentService;
import java.util.List;
import javax.validation.Valid;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Balnath
 */
@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;
     private static final Logger logger = LogManager.getLogger();
    @PostMapping("/save")
    public ResponseEntity<String> saveDeparment(@Valid @RequestBody Department department) {
        logger.info("In save department api");
        departmentService.saveDepartment(department);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/get-all-departments")
    public List<Department> getAllDepartment(){
        logger.info("In get all deparment method");
        return departmentService.getAllDepartment();
    }
}
