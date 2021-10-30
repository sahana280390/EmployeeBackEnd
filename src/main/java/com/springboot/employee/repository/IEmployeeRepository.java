package com.springboot.employee.repository;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.springboot.employee.model.Employee;

@Repository
public interface IEmployeeRepository extends  PagingAndSortingRepository<Employee, Long> {

} 


