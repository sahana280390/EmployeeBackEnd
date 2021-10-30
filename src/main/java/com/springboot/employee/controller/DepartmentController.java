package com.springboot.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.employee.model.Department;
import com.springboot.employee.repository.IDepartmentRepository;

@RestController
@RequestMapping("/api")
public class DepartmentController {
	
	@Autowired
	IDepartmentRepository departmentRepository;
	
	  @GetMapping("/departments")
	  public ResponseEntity<List<Department>> getAllDeparmtents() {
		  
		  try {
				List<Department> departmentList = departmentRepository.findAll();
				
				if (departmentList.isEmpty() || departmentList.size() == 0) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
				
				return new ResponseEntity<>(departmentList, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	  }

}
