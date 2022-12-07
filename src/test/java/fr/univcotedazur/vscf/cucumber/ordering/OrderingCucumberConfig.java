package fr.univcotedazur.vscf.cucumber.ordering;

import fr.univcotedazur.vscf.interfaces.Bank;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@CucumberContextConfiguration
@SpringBootTest
public class OrderingCucumberConfig {

    @Autowired // Bug in the Cucumber/Mockito/Spring coordination: needs to add @Autowired
    @MockBean
    private Bank bankMock;

}
