package io.github.kprasad99.feign.webflux.domain;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int empId;
	private String fName;
	private String lName;
	private LocalDate dob;
	private String department;
	
}
