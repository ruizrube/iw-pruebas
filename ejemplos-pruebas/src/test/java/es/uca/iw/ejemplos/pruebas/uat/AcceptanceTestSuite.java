package es.uca.iw.ejemplos.pruebas.uat;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import io.cucumber.spring.CucumberContextConfiguration;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("es/uca/iw/ejemplos/pruebas/uat")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "es/uca/iw/ejemplos/pruebas/uat")
public class AcceptanceTestSuite {
	
}