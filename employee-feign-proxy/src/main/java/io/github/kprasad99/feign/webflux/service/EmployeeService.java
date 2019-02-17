package io.github.kprasad99.feign.webflux.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.kprasad99.feign.webflux.domain.Employee;
import io.github.kprasad99.feign.webflux.proxy.EmployeeProxy;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/ui/v1/employee")
public class EmployeeService {

	@Autowired
	private EmployeeProxy employeeProxy;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Flux<Employee> list() {
		return Flux.fromIterable(employeeProxy.list());
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public Mono<ResponseEntity<Void>> create(Employee emp) {
		employeeProxy.create(emp);
		return Mono.justOrEmpty(ResponseEntity.accepted().build());
	}

	@PutMapping(value = "{empId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<ResponseEntity<Employee>> update(@PathVariable("empId") int empId, @RequestBody @Valid Employee emp) {
		return Mono.justOrEmpty(ResponseEntity.ok(employeeProxy.update(empId, emp)));
	}

	@DeleteMapping(value = "{empId}")
	public Mono<ResponseEntity<Void>> delete(@PathVariable("empId") int empId) {
		employeeProxy.delete(empId);
		return Mono.justOrEmpty(ResponseEntity.accepted().build());
	}
}