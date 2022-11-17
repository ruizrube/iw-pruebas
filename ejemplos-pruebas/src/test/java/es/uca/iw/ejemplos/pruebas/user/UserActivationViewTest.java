package es.uca.iw.ejemplos.pruebas.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
public class UserActivationViewTest {

	@Autowired
	private UserActivationView userView;

	@MockBean
	private UserService userService;

	@Test
	public void shouldShowFailureMessageWhenUserIsNotActivated() {

		// Given
		// a certain user
		User testUser = ObjectMother.createTestUser();

		// and the service is stubbed for the activateUser method
		given(userService.activateUser(anyString(), anyString())).willReturn(false);

		// When
		// Set form values
		userView.setEmail(testUser.getEmail());
		userView.setSecretCode(testUser.getRegisterCode());

		// and invoking the method onActivateButtonClick
		userView.onActivateButtonClick();

		// Then
		verify(userService, times(1)).activateUser(anyString(), anyString());
		// and
		assertThat(userView.getStatus().equals("Ups. The user could not be activated")).isTrue();
	}
	
	@Test
	public void shouldShowSuccessMessageWhenUserIsActivated() {

		// Given
		// a certain user
		User testUser = ObjectMother.createTestUser();

		// and the service is stubbed for the activateUser method
		given(userService.activateUser(anyString(), anyString())).willReturn(true);

		// When
		// Set form values
		userView.setEmail(testUser.getEmail());
		userView.setSecretCode(testUser.getRegisterCode());

		// and invoking the method onActivateButtonClick
		userView.onActivateButtonClick();

		// Then
		verify(userService, times(1)).activateUser(anyString(), anyString());
		// and
		assertThat(userView.getStatus().equals("Congrats. The user has been activated")).isTrue();
	}

	
}
