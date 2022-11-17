package es.uca.iw.ejemplos.pruebas.user;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class UserRestController {

	private UserService service;

	public UserRestController(UserService service) {
		this.service = service;
	}

	@GetMapping("/api/Users")
	List<User> all() {
		return service.loadActiveUsers();
	}

	@PostMapping("/api/Users")
	void newUser(@RequestBody User newUser) {
		service.registerUser(newUser);

	}

	// Single item

	@GetMapping("/api/Users/{id}")
	User one(@PathVariable Integer id) {

		return service.loadUserById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found"));
	}

	@PutMapping("/api/Users/{id}")
	User replaceUser(@RequestBody User newUser, @PathVariable Integer id) {

		// TO DO
		return newUser;
	}

	@DeleteMapping("/api/Users/{id}")
	void deleteUser(@PathVariable Integer id) {
		// TO DO
	}
}
