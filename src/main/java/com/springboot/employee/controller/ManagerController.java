package com.springboot.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.employee.model.Manager;
import com.springboot.employee.repository.IManagerRepository;

@RestController
@RequestMapping("/api")
public class ManagerController {
	
	@Autowired
	IManagerRepository managerRepository;
	
	  @GetMapping("/managers")
	  public ResponseEntity<List<Manager>> getAllDeparmtents() {
		  
		  try {
				List<Manager> managerList = managerRepository.findAll();
				
				if (managerList.isEmpty() || managerList.size() == 0) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
				
				return new ResponseEntity<>(managerList, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	  }

}