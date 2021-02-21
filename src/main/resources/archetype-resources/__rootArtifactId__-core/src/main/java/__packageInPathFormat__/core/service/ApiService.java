package ${package}.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ${package}.core.dto.ApiResponse;

@Service
public class ApiService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApiService.class);

	public ApiResponse isApiUp(final String name) {
		LOGGER.debug(">> isApiUp() name {}", name);

		ApiResponse response = new ApiResponse();
		response.setWelcomeMsg(name != null && !name.isBlank() ? "Hello " + name + "!" : "Hello World");

		LOGGER.debug("<< isApiUp() welcome message {}", response.getWelcomeMsg());
		return response;
	}

}
