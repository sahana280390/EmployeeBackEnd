package com.springboot.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.employee.model.Department;

@Repository
public interface IDepartmentRepository  extends  JpaRepository<Department, Long> {

} 
