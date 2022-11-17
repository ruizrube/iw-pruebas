package es.uca.iw.ejemplos.pruebas.it;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;

import es.uca.iw.ejemplos.pruebas.ObjectMother;
import es.uca.iw.ejemplos.pruebas.user.User;
import es.uca.iw.ejemplos.pruebas.user.UserService;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author ivanruizrube
 *
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserActivationViewIT {

	@LocalServerPort
	private int port;

	private String uribase = "http://127.0.0.1:";

	private WebDriver driver;

	@MockBean
	private UserService userService;

	@BeforeAll
	public static void setupClass() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void setUp() {

		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--no-sandbox");
		chromeOptions.addArguments("--headless");
		chromeOptions.addArguments("disable-gpu");
		chromeOptions.addArguments("--window-size=1920,1200");

		driver = new ChromeDriver(chromeOptions);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}

	@AfterEach
	public void teardown() {
		if (driver != null) {
			driver.close();

			driver.quit();
		}
	}

	@Test
	public void shouldShowFailureMessageWhenUserIsNotActivated() {

		// Given
		// a certain user
		User testUser = ObjectMother.createTestUser();

		// and the service is stubbed for the activateUser method
		given(userService.activateUser(anyString(), anyString())).willReturn(false);

		// When
		// point the browser to the activation page
		driver.get(uribase + port + "/useractivation");
	
		
		// and introduce form data
		WebElement email = driver.findElement(By.id("email"));
		email.sendKeys(testUser.getEmail());
		WebElement secretCode = driver.findElement(By.id("secretCode"));
		secretCode.sendKeys("key");

		// and press the activate button
		driver.findElement(By.id("activate")).click();

		// Then 
		WebElement element = driver.findElement(By.id("status"));
		assertThat(element.getText().equals("Ups. The user could not be activated")).isTrue();

		// and
		verify(userService, times(1)).activateUser(anyString(), anyString());


	}

	@Test
	public void shouldShowSuccessMessageWhenUserIsActivated() {

		// Given
		// a certain user
		User testUser = ObjectMother.createTestUser();

		// and the service is stubbed for the activateUser method
		given(userService.activateUser(anyString(), anyString())).willReturn(true);

		// When

		// point the browser to the activation page
		driver.get(uribase + port + "/useractivation");
		
		// and introduce form data
		WebElement email = driver.findElement(By.id("email"));
		email.sendKeys(testUser.getEmail());
		WebElement secretCode = driver.findElement(By.id("secretCode"));
		secretCode.sendKeys("key");

		// and press the activate button
		driver.findElement(By.id("activate")).click();

		// Then
		WebElement element = driver.findElement(By.id("status"));
		assertThat(element.getText().equals("Congrats. The user has been activated")).isTrue();

		// and
		verify(userService, times(1)).activateUser(anyString(), anyString());



	}

}
