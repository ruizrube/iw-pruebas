package es.uca.iw.ejemplos.pruebas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.vaadin.artur.helpers.LaunchUtil;

import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;

/**
 * The entry point of the Spring Boot application.
 *
 * Use the @PWA annotation make the application installable on phones, tablets
 * and some desktop browsers.
 *
 */
@PWA(name = "ejemplo", shortName = "ejemplo", offlineResources = {"images/logo.png"})
@NpmPackage(value = "line-awesome", version = "1.3.0")

@SpringBootApplication
@EnableAsync(proxyTargetClass = true)
@Push
public class Application extends SpringBootServletInitializer implements AppShellConfigurator  {

    public static void main(String[] args) {
        LaunchUtil.launchBrowserInDevelopmentMode(SpringApplication.run(Application.class, args));
    }
    
    

}
