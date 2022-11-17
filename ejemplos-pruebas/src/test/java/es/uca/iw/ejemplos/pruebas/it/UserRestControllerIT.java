package es.uca.iw.ejemplos.pruebas.it;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import es.uca.iw.ejemplos.pruebas.ObjectMother;
import es.uca.iw.ejemplos.pruebas.user.User;
import es.uca.iw.ejemplos.pruebas.user.UserService;

/**
 * @author ivanruizrube
 *
 */

@WebMvcTest
public class UserRestControllerIT {

	@Autowired
	private MockMvc server;

	@MockBean
	private UserService userService;

	@Test
	public void shouldReturnListOfUsers() {

		// Given
		// a certain user
		User testUser = ObjectMother.createTestUser();

		// and the service is stubbed for the method loadActiveUsers
		given(userService.loadActiveUsers()).willReturn(List.of(testUser));

		// When
		// Call the HTTP API
		String input = "/api/Users";

		// When make a HTTP API Rest invocation and assertion
		try {
			server.perform(get(input).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
					.andExpect(jsonPath("$", hasSize(1)))
					.andExpect(jsonPath("$[0].username", is(testUser.getUsername())));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
