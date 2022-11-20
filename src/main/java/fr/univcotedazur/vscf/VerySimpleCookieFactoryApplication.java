package fr.univcotedazur.vscf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class VerySimpleCookieFactoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(VerySimpleCookieFactoryApplication.class, args);
	}

}
