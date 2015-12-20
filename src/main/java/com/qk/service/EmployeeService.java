package com.qk.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.qk.entity.Employee;

@Service
public class EmployeeService {

	private static Map<String, Employee> employees = new HashMap<String, Employee>();

	static {
		Employee employee = new Employee("Quamrul", "Khan");
		employees.put(employee.getEId(), employee);
		employee = new Employee("Jon", "Walsh");
		employees.put(employee.getEId(), employee);
		employee = new Employee("Chris", "Breisch");
		employees.put(employee.getEId(), employee);
		employee = new Employee("Jake", "Bauer");
		employees.put(employee.getEId(), employee);
	}

	public Collection<Employee> getAll() {
		return employees.values();
	}

	public Employee findById(String id) {
		return employees.get(id);
	}

	public void save(Employee employee) {
		employees.put(employee.getEId(), employee);
	}

	public void update(Employee employee) {
		employees.put(employee.getEId(), employee);
	}

	public void delete(String id) {
		employees.remove(id);
	}
}
