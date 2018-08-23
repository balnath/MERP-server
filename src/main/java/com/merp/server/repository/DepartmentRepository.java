/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merp.server.repository;

import com.merp.server.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Balnath
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{

    Department findByName(String departmentName);
        
}
