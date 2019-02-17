package io.github.kprasad99.backend.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.kprasad99.backend.dao.EmployeeRepository;
import io.github.kprasad99.backend.domain.Employee;

@RestController
@RequestMapping("/v1/employee")
public class EmployeeService {

	@Autowired
	private EmployeeRepository empRepo;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Employee> list() {
		return StreamSupport.stream(empRepo.findAll().spliterator(), true).collect(Collectors.toList());
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public void create(Employee emp) {
		empRepo.save(emp);
	}

	@PutMapping(value = "{empId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Employee update(@PathVariable("empId") int empId, @RequestBody @Valid Employee emp) {
		int count = empRepo.update(emp.getFName(), emp.getLName(), emp.getDob(), emp.getDepartment(), empId);
		if (count > 0) {
			return emp;
		}

		return null;
	}

	@DeleteMapping(value = "{empId}")
	public void delete(@PathVariable("empId") int empId) {
		empRepo.deleteById(empId);
	}
}