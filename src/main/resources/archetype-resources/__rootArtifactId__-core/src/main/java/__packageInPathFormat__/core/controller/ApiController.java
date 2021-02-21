package ${package}.core.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@GetMapping(value = {"/hello"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponse isApiUp(@RequestParam final String name) {
		LOGGER.info(">> isApiUp() name {}", name);

		ApiResponse response = service.isApiUp(name);

		LOGGER.info("<< isApiUp() response {}", response);
		return response;
	}
}
