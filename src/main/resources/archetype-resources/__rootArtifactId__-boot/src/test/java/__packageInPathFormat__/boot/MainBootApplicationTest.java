package ${package}.boot;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties", properties = { "server.port=8080" })
public class MainBootApplicationTest {

	@Autowired
	private RestTemplate restTemplate;

	@Before
	public void init() {
		MockRestServiceServer.createServer(restTemplate);
	}

	@Test
	public void contextLoad() {
		Assertions.assertThat(SpringBootVersion.getVersion()).isNotNull();
	}

}


