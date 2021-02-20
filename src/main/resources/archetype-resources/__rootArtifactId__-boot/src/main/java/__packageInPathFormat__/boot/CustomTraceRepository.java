package ${package}.boot;

import java.time.ZoneId;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CustomTraceRepository implements HttpTraceRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomTraceRepository.class);

	AtomicReference<HttpTrace> lastTrace = new AtomicReference<>();

	@Override
	public List<HttpTrace> findAll() {
		return Collections.singletonList(lastTrace.get());
	}

	@Override
	public void add(HttpTrace trace) {
		Map<String, List<String>> headers = new HashMap<>(trace.getRequest().getHeaders());
		headers.remove("authorization");
		LOGGER.info("\n Time: {} \n Principal: {} \n Request: {}:{} \n Request Headers: {} \n status: {}",
				trace.getTimestamp().atZone(ZoneId.of("Europe/Madrid")),
				trace.getPrincipal().getName(),
				trace.getRequest().getMethod(),
				trace.getRequest().getUri(),
				headers,
				trace.getResponse().getStatus());
		lastTrace.set(trace);
	}

}