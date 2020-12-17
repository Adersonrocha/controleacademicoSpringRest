package br.com.controleAcademico.model.api.rest;

public enum Funcao {

	PROFESSOR ("Professor"),
	ALUNO ("Aluno"),
	COORDENADOR ("Coordenador"),
	SECRETARIO ("Secretário"),
	SUPORTE ("Suporte"),
	PLENO ("Pleno"),
	SENIOR ("Sênior"),
	JUNIOR ("Júnior"),
	MASTER ("Master"),
	ESTAGIARIO ("Estagiário");
	
	private final String name;
	
	Funcao(String name){
		 this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
