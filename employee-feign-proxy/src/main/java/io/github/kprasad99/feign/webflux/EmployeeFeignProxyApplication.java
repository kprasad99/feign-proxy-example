package io.github.kprasad99.feign.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableFeignClients
public class EmployeeFeignProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeFeignProxyApplication.class, args);
	}

}

