package com.rishabh.thymeleaf.thymeleafdemo.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rishabh.thymeleaf.thymeleafdemo.entity.Employee;
import com.rishabh.thymeleaf.thymeleafdemo.service.EmployeeService;

@Controller
@RequestMapping("employees")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/list")
	public String sayHello(Model theModel) {
		List<Employee> emplist = employeeService.findAll().stream().sorted((e1,e2)->e1.getFirstName().compareToIgnoreCase(e2.getFirstName())).collect(Collectors.toList());
		theModel.addAttribute("employees",emplist);
		
		return "employees";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel){
		Employee e = new Employee();
		
		theModel.addAttribute("employee",e);
		
		return "employee-form";
	}
	
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee e) {
		
		employeeService.save(e);
		
		return "redirect:/employees/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int id,Model theModel) {
		
		Employee e = employeeService.findById(id);
		theModel.addAttribute("employee",e);
		
		return "employee-form";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int id) {
		
		employeeService.deleteById(id);
		
		return "redirect:/employees/list";
	}
	

}
