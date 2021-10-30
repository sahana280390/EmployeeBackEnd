package com.springboot.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.employee.model.Manager;

@Repository
public interface IManagerRepository extends  JpaRepository<Manager, Long> {

} 
