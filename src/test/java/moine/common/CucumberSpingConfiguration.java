package moine.common;


import moine.AuthApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = { AuthApplication.class })
public class CucumberSpingConfiguration {
    
}
