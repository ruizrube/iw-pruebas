package es.uca.iw.ejemplos.pruebas.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.uca.iw.ejemplos.pruebas.ObjectMother;

/**
 * @author ivanruizrube
 *
 */

//DataJpaTest es equivalente a poner las siguientes etiquetas:
//@Transactional(propagation = Propagation.REQUIRED)
//@AutoConfigureTestDatabase(replace=Replace.ANY)
//@SpringBootTest 

@DataJpaTest 
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void shouldNotFindANotExistingUser() {

		// Given
		// a random user Id
		Integer userId = 123;

		// When invoking the method
		Optional<User> foundUser = userRepository.findById(userId);

		// Then
		assertThat(foundUser.isPresent()).isFalse();

	}

	@Test
	public void shouldFindAnExistingUser() {

		// Given
		// a certain user
		User testUser = ObjectMother.createTestUser();
		// stored in the repository
		userRepository.save(testUser);

		// When invoking the method findById
		Optional<User> foundUser = userRepository.findById(testUser.getId());

		// Then
		assertThat(foundUser.get()).isEqualTo(testUser);

	}
}
