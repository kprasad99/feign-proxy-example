package io.github.kprasad99.feign.webflux.proxy;

import java.util.List;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.github.kprasad99.feign.webflux.domain.Employee;

@FeignClient(value = "empProxy", path = "/v1/employee", url = "http://localhost:8080")
public interface EmployeeProxy {

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Employee> list();

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public void create(Employee emp);

	@PutMapping(value = "{empId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Employee update(@PathVariable("empId") int empId, @RequestBody @Valid Employee emp);

	@DeleteMapping(value = "{empId}")
	public void delete(@PathVariable("empId") int empId);
}
