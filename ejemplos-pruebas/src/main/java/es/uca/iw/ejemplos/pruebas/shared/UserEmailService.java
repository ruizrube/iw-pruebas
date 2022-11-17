package es.uca.iw.ejemplos.pruebas.shared;

import es.uca.iw.ejemplos.pruebas.user.User;

public interface UserEmailService {

	boolean sendRegistrationEmail(User user);

}