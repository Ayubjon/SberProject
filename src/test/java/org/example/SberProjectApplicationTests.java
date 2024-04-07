package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.request.FactorialRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigInteger;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class SberProjectApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	protected ResultActions perform(MockHttpServletRequestBuilder builder) throws Exception {
		return mockMvc.perform(builder);
	}

	static final String URL = "/api/v1/get-factorial";

	@Test
	void testFactorial10() throws Exception {

		String request = asJsonString(new FactorialRequest(10));

		perform(MockMvcRequestBuilders.get(URL)
				.contentType(MediaType.APPLICATION_JSON)
				.content(request))
				.andExpect(status().is2xxSuccessful())
				.andExpect(jsonPath("$.result").value("3628800"));
	}

	@Test
	void testFactorialBigNumber() throws Exception {

		final int bigInt = 10_000;

		String request = asJsonString(new FactorialRequest(bigInt));

		BigInteger ans = BigInteger.ONE;
		for (int i = 1; i <= bigInt; ++i) {
			ans = ans.multiply(BigInteger.valueOf(i));
		}

		perform(MockMvcRequestBuilders.get(URL)
				.contentType(MediaType.APPLICATION_JSON)
				.content(request))
				.andExpect(status().is2xxSuccessful())
				.andExpect(jsonPath("$.result").value(ans));
	}

	@Test
	void testFactorialZeroEqualsOne() throws Exception {

		String request = asJsonString(new FactorialRequest(0));

		perform(MockMvcRequestBuilders.get(URL)
				.contentType(MediaType.APPLICATION_JSON)
				.content(request))
				.andExpect(status().is2xxSuccessful())
				.andExpect(jsonPath("$.result").value(1));
	}

	@Test
	void testFactorialNegativeNumber() throws Exception {

		String expectedMessage = "400 BAD_REQUEST \"Number should be between 0 and 100000.\"";

		String request = asJsonString(new FactorialRequest(-10));
		perform(MockMvcRequestBuilders.get(URL)
				.contentType(MediaType.APPLICATION_JSON)
				.content(request))
				.andExpect(status().is4xxClientError())
				.andExpect(jsonPath("$.message").value(expectedMessage));
	}

	@Test
	void testFactorialVeryBigInt() throws Exception {

		String expectedMessage = "400 BAD_REQUEST \"Number should be between 0 and 100000.\"";

		String request = asJsonString(new FactorialRequest(1_000_000));
		perform(MockMvcRequestBuilders.get(URL)
				.contentType(MediaType.APPLICATION_JSON)
				.content(request))
				.andExpect(status().is4xxClientError())
				.andExpect(jsonPath("$.message").value(expectedMessage));
	}

	@Test
	void testFactorialMissingIntegerWithExample() throws Exception {

		String expectedExample = "{\"factorial_num\": 100}";

		String request = asJsonString(new FactorialRequest());
		perform(MockMvcRequestBuilders.get(URL)
				.contentType(MediaType.APPLICATION_JSON)
				.content(request))
				.andExpect(status().is4xxClientError())
				.andExpect(jsonPath("$.example").value(expectedExample));
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

