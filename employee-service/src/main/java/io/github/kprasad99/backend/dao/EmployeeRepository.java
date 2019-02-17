package io.github.kprasad99.backend.dao;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import io.github.kprasad99.backend.domain.Employee;

@Transactional
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Modifying
	@Query("update Employee e set e.fName = ?1, e.lName=?2, e.dob=?3, e.department=?4 where e.empId=?5")
	int update(String fName, String lName, LocalDate dob, String department, int empId);
}
