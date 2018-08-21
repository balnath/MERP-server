/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merp.server.serviceImpl;

import com.merp.server.model.Department;
import com.merp.server.repository.DepartmentRepository;
import com.merp.server.service.DepartmentService;
import java.util.List;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 *
 * @author Balnath
 */
@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    @Inject
    DepartmentRepository departmentRepository;
    @Override
    public void saveDepartment(Department department) {
        departmentRepository.save(department);
    }

    @Override
    public List<Department> getAllDepartment() {
        return (List<Department>) departmentRepository.findAll();
    }
    
}
