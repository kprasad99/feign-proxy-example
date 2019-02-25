package io.github.kprasad99.feign.webflux;

import java.net.URI;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration
@ConditionalOnProperty(prefix="docker", name="instance", havingValue="true")
public class WebFluxConfig {
	
	@Bean
	public RouterFunction<ServerResponse> staticContent() {
		return RouterFunctions.resources("/**", new FileSystemResource("/static/"));
	}
	
	@Bean
	public RouterFunction<ServerResponse> rootRedirect() {
		return RouterFunctions.route(GET("/"), req -> ServerResponse.temporaryRedirect(URI.create("/index.html")).build());
	}
}
