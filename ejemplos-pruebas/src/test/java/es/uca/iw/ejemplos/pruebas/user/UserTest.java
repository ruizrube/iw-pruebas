package es.uca.iw.ejemplos.pruebas.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import es.uca.iw.ejemplos.pruebas.ObjectMother;

/**
 * @author ivanruizrube
 *
 */

@SpringBootTest
public class UserTest {

	@Test
	public void shouldProvideUsername() {

		// Given
		// a certain user (not stored on the database)
		User testUser = ObjectMother.createTestUser("john");

		// When
		// I invoke getUsername method
		String username = testUser.getUsername();
		
		// Then the result is equals to the provided username
		assertThat(username.equals("john")).isTrue();

	}

}
