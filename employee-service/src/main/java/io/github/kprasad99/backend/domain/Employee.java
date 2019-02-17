package io.github.kprasad99.backend.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tbl_employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int empId;
	private String fName;
	private String lName;
	private LocalDate dob;
	private String department;
	
}
