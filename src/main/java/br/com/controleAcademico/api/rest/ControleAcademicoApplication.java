package br.com.controleAcademico.api.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EntityScan(basePackages = {"br.com.controleAcademico.model.api.rest"})
@ComponentScan(basePackages = {"br.com.controleAcademico.*"})
@EnableJpaRepositories(basePackages = {"br.com.controleAcademico.repository.api.rest"})
@EnableTransactionManagement
@EnableWebMvc
@RestController
@EnableAutoConfiguration
public class ControleAcademicoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControleAcademicoApplication.class, args);
		//System.out.println(new BCryptPasswordEncoder().encode("123")); 
	}

}
