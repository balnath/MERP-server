/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merp.server.service;

import com.merp.server.model.Department;
import java.util.List;

/**
 *
 * @author Balnath
 */
public interface DepartmentService {
    
    public void saveDepartment(Department department);
    
    public List<Department> getAllDepartment();
}
