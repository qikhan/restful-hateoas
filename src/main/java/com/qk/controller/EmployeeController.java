package com.qk.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qk.entity.Employee;
import com.qk.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@RequestMapping(value = "/employees", method = RequestMethod.GET)
	@ResponseBody
	public HttpEntity<List<Employee>> getAll() {

		List<Employee> employees = new ArrayList<Employee>(
				employeeService.getAll());
		employees.stream().forEach(
				employee -> {
					if (employee.getLinks().isEmpty()) {
						employee.add(linkTo(
								methodOn(EmployeeController.class).get(
										employee.getEId())).withSelfRel());
					}
				});

		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}

	@RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
	@ResponseBody
	public HttpEntity<Employee> get(@PathVariable("id") String id) {

		Employee employee = employeeService.findById(id);
		if (employee != null) {
			return new ResponseEntity<Employee>(employee, HttpStatus.OK);
		}
		return new ResponseEntity<Employee>(employee, HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/employee", method = RequestMethod.POST)
	public ResponseEntity<Void> createUser(@RequestBody Employee employee) {
		System.out.println("Creating Employee " + employee.getFirstName());

		// TODO validation
		// return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		employeeService.save(employee);

		HttpHeaders headers = new HttpHeaders();
		ControllerLinkBuilder link = linkTo(methodOn(EmployeeController.class)
				.get(employee.getEId()));
		employee.add(link.withSelfRel());
		headers.setLocation(link.toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/employee/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Employee> update(@PathVariable("id") String id,
			@RequestBody Employee updateEmployee) {
		System.out.println("Updating Employee " + id);

		Employee employee = employeeService.findById(id);

		if (employee == null) {
			System.out.println("Employee with id " + id + " not found");
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}

		employee.setFirstName(updateEmployee.getFirstName());
		employee.setLastName(updateEmployee.getLastName());

		employeeService.update(employee);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	@RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Employee> delete(@PathVariable("id") String id) {
		System.out.println("Fetching & Deleting Employee with id " + id);

		Employee employee = employeeService.findById(id);
		if (employee == null) {
			System.out.println("Unable to delete. Employee with id " + id
					+ " not found");
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}

		employeeService.delete(id);
		return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
	}
}