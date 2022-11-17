package es.uca.iw.ejemplos.pruebas.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String email);

	List<User> findByActiveTrue();

	Optional<User> findByUsername(String username);
}