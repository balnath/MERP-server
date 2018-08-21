/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merp.server.service;

import com.merp.server.model.Department;
import java.util.List;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Balnath
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class DepartmentServiceTest {
    @Inject
    DepartmentService departmentService;
    
    @Test
    public void testSaveDepartment(){
        Department department = new Department();
        department.setActive(true);
        department.setDescription("This is test department");
        department.setName("OPD");
        departmentService.saveDepartment(department);
        List<Department> department1 =  departmentService.getAllDepartment();
        Assert.assertNotNull(department1);
    }
}
