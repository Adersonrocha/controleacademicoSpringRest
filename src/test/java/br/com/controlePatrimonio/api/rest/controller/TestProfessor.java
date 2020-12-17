package br.com.controlePatrimonio.api.rest.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;

import br.com.controleAcademico.controller.api.rest.ProfessorController;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

@WebMvcTest
public class TestProfessor {

	@Autowired
	private ProfessorController professorController;
	
	
	@BeforeEach
	public void setup() {
		RestAssuredMockMvc.standaloneSetup(this.professorController); // spring so carrega o contexto da classe que queremos
	}
	

	@Test
	public void cadastraProfessor_esperaStatusOk() {
		RestAssuredMockMvc.given()
		.accept(ContentType.JSON)
		.when().post("localhost:8080/controleacademico/professor/")
		.then().statusCode(HttpStatus.OK.value());
	}
}
