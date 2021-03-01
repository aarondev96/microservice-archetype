package ${package}.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ${package}.core.dto.ApiDto;

@Service
public class ApiService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApiService.class);

	public ApiDto isApiUp() {
		LOGGER.debug(">> isApiUp()");

		ApiDto response = new ApiDto("Hello World");

		LOGGER.debug("<< isApiUp() welcome message {}", response.getWelcomeMsg());
		return response;
	}

}
