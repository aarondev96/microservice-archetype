package ${package}.core.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ${package}.core.dto.ApiResponse;
import ${package}.core.service.ApiService;

@RestController
public class ApiController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApiController.class);

	private final ApiService service;

	public ApiController(ApiService service) {
		this.service = service;
	}

	@GetMapping(value = {"/hello", "/hello/{name}"}, produces = MediaType.TEXT_PLAIN_VALUE)
	public ApiResponse helloRest(@PathVariable final String name) {
		LOGGER.info(">> helloRest() name {}", name);

		ApiResponse response = service.hello(name);

		LOGGER.info("<< helloRest() response {}", response);
		return response;
	}
}
