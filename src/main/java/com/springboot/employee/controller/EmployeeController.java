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

	   /* @GetMapping("/employees")
	    public ResponseEntity<List<Employee>> getAllEmployees() {
	    	try {
				List<Employee> list = employeeRepository.findAll();
				
				if (list.isEmpty() || list.size() == 0) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
				
				return new ResponseEntity<>(list, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	    }*/
	    
	    @GetMapping("/employees")
	    public Page<Employee> list(@RequestParam(name = "page", defaultValue = "0") int page,
	                                   @RequestParam(name = "size", defaultValue = "10") int size) {
	      PageRequest pageRequest = PageRequest.of(page, size);
	      Page<Employee> pageResult = employeeRepository.findAll(pageRequest);
	      List<Employee> employeeList = pageResult.toList();

	      return new PageImpl<>(employeeList, pageRequest, pageResult.getTotalElements());

	    }
	    
	   

	    @GetMapping("/employees/{id}")
	    public ResponseEntity < Employee > getEmployeeById(@PathVariable(value = "id") Long employeeId)
	    throws ResourceNotFoundException {
	        Employee employee = employeeRepository.findById(employeeId)
	            .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
	        return ResponseEntity.ok().body(employee);
	    }

	    @PostMapping("/employees")
	    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
	    	try {
				return new ResponseEntity<>(employeeRepository.save(employee), HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
	    }
	    
	 

	    @PutMapping("/employees/{id}")
	    public ResponseEntity <Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
	         @RequestBody Employee employee) throws ResourceNotFoundException {
	        
	        final Employee updatedEmployee = employeeRepository.save(employee);
	        return ResponseEntity.ok(updatedEmployee);
	    }

	    @DeleteMapping("/employees/{id}")
	    public Map < String, Boolean > deleteEmployee(@PathVariable(value = "id") Long employeeId)
	    throws ResourceNotFoundException {
	        Employee employee = employeeRepository.findById(employeeId)
	            .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

	        employeeRepository.delete(employee);
	        Map < String, Boolean > response = new HashMap < > ();
	        response.put("deleted", Boolean.TRUE);
	        return response;
	    }

}
