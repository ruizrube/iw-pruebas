package es.uca.iw.ejemplos.pruebas.user;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uca.iw.ejemplos.pruebas.shared.UserEmailService;

@Service
public class UserService {

	private UserRepository repository;

	private UserEmailService emailService;

	@Autowired
	public UserService(UserRepository repository, UserEmailService emailService) {
		this.repository = repository;
		this.emailService = emailService;
	}

	public Optional<User> loadUserByUsername(String username) {
		return repository.findByUsername(username);

	}

	public Optional<User> loadUserById(Integer userId) {
		return repository.findById(userId);
	}

	public List<User> loadActiveUsers() {
		return repository.findByActiveTrue();
	}

	public void delete(User testUser) {
		repository.delete(testUser);

	}

	public void registerUser(User user) {
		user.setPassword("codedpassword");
		user.setRegisterCode(UUID.randomUUID().toString().substring(0, 5));
		repository.save(user);
		emailService.sendRegistrationEmail(user);
	}

	public boolean activateUser(String email, String registerCode) {

		Optional<User> user = repository.findByEmail(email);

		if (user.isPresent() && user.get().getRegisterCode().equals(registerCode)) {
			user.get().setActive(true);
			repository.save(user.get());
			return true;

		} else {
			return false;
		}

	}

}
