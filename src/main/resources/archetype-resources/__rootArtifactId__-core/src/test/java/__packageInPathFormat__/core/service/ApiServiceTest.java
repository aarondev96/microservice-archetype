package ${package}.core.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
public class ApiServiceTest {

	@TestConfiguration
	static class ApiServiceTestContextConfiguration {

		@Bean
		public ApiService apiService() {
			return new ApiService();
		}

		@Bean
		public RestTemplate restTemplate() {
			return new RestTemplate();
		}

	}

	@Autowired
	private ApiService apiService;

	@Test
	public void whenHelloEmpty() {
		String name = "";
		String greeting = apiService.hello(name).getWelcomeMsg();
		Assert.assertEquals("Hello World", greeting);
	}

	@Test
	public void whenHelloName() {
		String name = "test";
		String greeting = apiService.hello(name).getWelcomeMsg();
		Assert.assertEquals("Hello test!", greeting);
	}

}
