package es.uca.iw.ejemplos.pruebas.it;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.uca.iw.ejemplos.pruebas.ObjectMother;
import es.uca.iw.ejemplos.pruebas.user.User;
import es.uca.iw.ejemplos.pruebas.user.UserService;

/**
 * @author ivanruizrube
 *
 */

@SpringBootTest
@Transactional(propagation = Propagation.REQUIRES_NEW) // después de cada test se hace un rollback de la base de datos
public class UserServiceIT {

	@Autowired
	private UserService userService;

	User testUser;

	
	@Test
	public void shouldActivateAnExistingUser() {

		// Given
		// a certain user
		User testUser = ObjectMother.createTestUser();

		// who is registered
		userService.registerUser(testUser);

		// When invoking the method ActivateUser
		boolean result = userService.activateUser(testUser.getEmail(), testUser.getRegisterCode());

		// Then the result method is true
		assertThat(result).isTrue();

		// When invoking the method FindActive
		List<User> returnedUsers = userService.loadActiveUsers();

		// Then the result includes the user
		assertThat(returnedUsers.contains(testUser)).isTrue();

	}
	
	
	
	@Test
	public void shouldNotActivateANoExistingUser() {

		// Given
		// a certain user (not stored on the database)
		User testUser = ObjectMother.createTestUser();

		// When invoking the method ActivateUser
		boolean result = userService.activateUser(testUser.getEmail(), testUser.getRegisterCode());

		// Then the result method is false
		assertThat(result).isFalse();

		// When invoking the method FindActiveUsers
		List<User> returnedUsers = userService.loadActiveUsers();

		// Then the result does not include the user
		assertThat(returnedUsers.contains(testUser)).isFalse();

	}

}
