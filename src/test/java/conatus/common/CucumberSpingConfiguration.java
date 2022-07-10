package conatus.common;

import conatus.AuthApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = { AuthApplication.class })
public class CucumberSpingConfiguration {}
