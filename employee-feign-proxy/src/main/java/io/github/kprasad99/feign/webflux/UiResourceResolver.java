package io.github.kprasad99.feign.webflux;

import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileUrlResource;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@ConditionalOnProperty(prefix = "emp", name = "ui.path")
public class UiResourceResolver implements WebFluxConfigurer {

	@Value("${emp.ui.path}")
	private String path;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		if (path != null && !path.isEmpty()) {
			registry.addResourceHandler("/resources/**").addResourceLocations("/resources/", path);
			System.out.println("Added path:" + path);
		}
		// WebFluxConfigurer.super.addResourceHandlers(registry);
	}

	@Bean
	RouterFunction<ServerResponse> staticResourceRouter() throws MalformedURLException {
		return RouterFunctions.resources("/**", new FileUrlResource(path));
	}
}
