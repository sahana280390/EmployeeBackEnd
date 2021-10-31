package com.springboot.employee;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.springboot.employee.controller.EmployeeController;
import com.springboot.employee.model.Department;
import com.springboot.employee.model.Employee;
import com.springboot.employee.repository.IEmployeeRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class EmployeeAppApplicationTests {

	
	@InjectMocks
    EmployeeController employeeController;
	
    @Mock
    IEmployeeRepository employeeRepository;
    
   
    @Test
    public void fetchAllEmployees() throws Exception {
    	Department dep = new Department();
    	dep.setDepartmentId(1L);
    	dep.setDepartmentName("Finance");
       Employee emp = new Employee();
       emp.setCreatedAt(null);
       emp.setDepartment(dep);
       emp.setEmailId("sd@g.com");
       emp.setEmployeeId(1);
       emp.setFirstName("Sahana");
       emp.setHireDate(null);
       emp.setLastName("Devd");
       emp.setManager(1L);
       emp.setPhoneNumber("123-456-8596");
       emp.setSalary(123.26);
       emp.setUpdatedAt(null);
       List<Employee> employees = Arrays.asList(emp);
       PageRequest pageRequest = PageRequest.of(0, 10);
       Page<Employee> empListPageable = new PageImpl<>(employees, pageRequest, 1);
       
        when(employeeRepository.findAll(pageRequest)).thenReturn(empListPageable);
        //test
        Page<Employee> empList = employeeController.getAllEmployees(0,10);
         
        assertEquals(1, empList.getTotalElements());
        verify(employeeRepository, times(1)).findAll(pageRequest);

    }
    
    @Test
    public void saveEmployee() throws Exception {
    	Department dep = new Department();
    	dep.setDepartmentId(1L);
    	dep.setDepartmentName("Finance");
       Employee emp = new Employee();
       emp.setCreatedAt(null);
       emp.setDepartment(dep);
       emp.setEmailId("sd@g.com");
       emp.setEmployeeId(1);
       emp.setFirstName("Sahana");
       emp.setHireDate(null);
       emp.setLastName("Devd");
       emp.setManager(1L);
       emp.setPhoneNumber("123-456-8596");
       emp.setSalary(123.26);
       emp.setUpdatedAt(null);
    	
       employeeController.saveEmployee(emp);
       assertEquals("Sahana", emp.getFirstName());
       
       verify(employeeRepository, times(1)).save(emp);
    }
    

}
