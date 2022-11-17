package es.uca.iw.ejemplos.pruebas.user;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@PageTitle("Registrate User")
@Route(value = "userregistration")
@RouteAlias("")
public class UserRegistrationView extends VerticalLayout {

	private static final long serialVersionUID = 851217309689685413L;

	private UserService service;

	private TextField username;
	private EmailField email;
	private PasswordField password;

	private Button register;
	private H4 status;

	private BeanValidationBinder<User> binder;

	public UserRegistrationView(UserService service) {
		this.service = service;

		username = new TextField("Your username");
		username.setId("username");

		email = new EmailField("Your email");
		email.setId("email");

		password = new PasswordField("Your password");
		password.setId("password");

		register = new Button("Register");
		register.setId("register");

		status = new H4();
		status.setId("status");
		status.setVisible(false);

		setMargin(true);

		add(username, email, password, register, status);

		register.addClickListener(e -> onRegisterButtonClick());

		binder = new BeanValidationBinder<>(User.class);
		binder.bindInstanceFields(this);

		binder.setBean(new User());
	}

	/**
	 * Handler
	 */
	public void onRegisterButtonClick() {

		if (binder.validate().isOk()) {
			service.registerUser(binder.getBean());
			status.setText("Great. Please look at your mail inbox!");
			status.setVisible(true);

		} else {
			Notification.show("Please, check input data");
		}

	}

}
