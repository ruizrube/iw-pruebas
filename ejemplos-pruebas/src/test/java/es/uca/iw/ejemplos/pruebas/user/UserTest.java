package es.uca.iw.ejemplos.pruebas.user;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
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
