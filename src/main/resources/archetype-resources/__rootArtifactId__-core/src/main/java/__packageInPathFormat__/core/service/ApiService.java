package ${package}.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

import ${package}.core.dto.ApiResponse;

@Service
public class ApiService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApiService.class);

	public ApiResponse hello(final String name) {
		LOGGER.debug(">> hello() name {}", name);

		ApiResponse response = new ApiResponse();
		response.setWelcomeMsg(name != null && !name.isBlank() ? "Hello " + name + "!" : "Hello World");

		LOGGER.debug("<< hello() welcome message {}", response.getWelcomeMsg());
		return response;
	}

}
