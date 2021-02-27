package ${package}.jpa.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ApiMapper {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApiMapper.class);

	public ApiMapper apiToApi() {
		LOGGER.debug(">> apiToApi()");
		LOGGER.debug("<< apiToApi()");
		return new ApiMapper();
	}

}
