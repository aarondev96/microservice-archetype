package ${package}.core.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ${package}.core.dto.ApiDto;
import ${package}.core.service.ApiService;

@RestController
@Api(value = "Api controller", tags = "Api controller")
@SwaggerDefinition(tags = {@Tag(name = "Api controller", description = "Api controller")})
public class ApiController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApiController.class);

	private final ApiService service;

	public ApiController(ApiService service) {
		this.service = service;
	}

	@GetMapping(value = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Return a basic greeting", response = String.class, responseReference = "Hello World")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Return a basic greeting", response = String.class)
	})
	public ApiDto isApiUp() {
		LOGGER.info(">> isApiUp()");

		ApiDto response = service.isApiUp();

		LOGGER.info("<< isApiUp() response {}", response);
		return response;
	}
}
