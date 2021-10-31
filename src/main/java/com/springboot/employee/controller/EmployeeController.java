package com.springboot.employee.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.springboot.employee.exception.ResourceNotFoundException;
import com.springboot.employee.model.Employee;
import com.springboot.employee.repository.IEmployeeRepository;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	
	 @Autowired
	 private IEmployeeRepository employeeRepository;

	   
	    
	    @GetMapping("/employees")
	    public Page<Employee> getAllEmployees(@RequestParam(name = "page", defaultValue = "0") int page,
	                                   @RequestParam(name = "size", defaultValue = "10") int size) {
	      PageRequest pageRequest = PageRequest.of(page, size);
	      Page<Employee> pageResult = employeeRepository.findAll(pageRequest);
	      List<Employee> employeeList = pageResult.toList();
	      return new PageImpl<>(employeeList, pageRequest, pageResult.getTotalElements());

	    }
	    
	   

	    @GetMapping("/employees/{id}")
	    public ResponseEntity <Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
	    throws ResourceNotFoundException {
	        Employee employee = employeeRepository.findById(employeeId)
	            .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
	        return ResponseEntity.ok().body(employee);
	    }

	    @PostMapping("/employees")
	    public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Employee employee) {
	    	try {
				return new ResponseEntity<>(employeeRepository.save(employee), HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
	    }
	    


	    @DeleteMapping("/employees/{id}")
	    public Map<String,Boolean > deleteEmployee(@PathVariable(value = "id") Long employeeId)
	    throws ResourceNotFoundException {
	        Employee employee = employeeRepository.findById(employeeId)
	            .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

	        employeeRepository.delete(employee);
	        Map <String, Boolean > response = new HashMap < > ();
	        response.put("Employee with employeeId" + employeeId + "deleted successfully", Boolean.TRUE);
	        return response;
	    }

}
