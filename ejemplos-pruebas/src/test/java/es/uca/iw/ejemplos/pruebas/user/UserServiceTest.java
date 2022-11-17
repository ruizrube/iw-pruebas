package es.uca.iw.ejemplos.pruebas.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import es.uca.iw.ejemplos.pruebas.ObjectMother;
import es.uca.iw.ejemplos.pruebas.shared.UserEmailService;

/**
 * @author ivanruizrube
 *
 */

@SpringBootTest
public class UserServiceTest {

	@Autowired
	private UserService userService;

	@MockBean
	private UserRepository userRepository;

	@MockBean
	private UserEmailService emailService;

	User testUser;

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

	@Test
	public void shouldActivateAnExistingUser() {

		// Given
		// a certain user
		User testUser = ObjectMother.createTestUser();

		// stored on the repo
		userService.registerUser(testUser);

		// and the repo methods are stubbed
		given(userRepository.findByEmail(anyString())).willReturn(Optional.of(testUser));
		given(userRepository.findByActiveTrue()).willReturn(List.of(testUser));

		// When invoking the method ActivateUser
		boolean result = userService.activateUser(testUser.getEmail(), testUser.getRegisterCode());

		// Then the result method is true
		assertThat(result).isTrue();

		// When invoking the method FindActive
		List<User> returnedUsers = userService.loadActiveUsers();

		// Then the result includes the user
		assertThat(returnedUsers.contains(testUser)).isTrue();

	}

}
