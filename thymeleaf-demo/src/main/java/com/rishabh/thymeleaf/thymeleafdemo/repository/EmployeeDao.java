package com.rishabh.thymeleaf.thymeleafdemo.repository;

import java.util.List;

import com.rishabh.thymeleaf.thymeleafdemo.entity.Employee;


public interface EmployeeDao {

	public List<Employee> findAll();
	
	public Employee findById(int theId);
	
	public void save(Employee theEmployee);
	
	public void deleteById(int theId);
	
}
