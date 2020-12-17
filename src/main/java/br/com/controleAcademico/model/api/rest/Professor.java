package br.com.controleAcademico.model.api.rest;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Professor implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue private Long id;			  
	private String nome;
				 

	private String areaAtuacao;
	
	private Long matricula;
	
	private String formacao;
	
	private String mestrado;
	
	private String doutorado;
	
	
	  public Long getId() { return id; }
	  
	  public void setId(Long id) { this.id = id; }
	  
	  public String getNome() { return nome; }
	  
	  public void setNome(String nome) { this.nome = nome; }
	 

	public String getareaAtuacao() {
		return areaAtuacao;
	}

	public void setEspecialidade(String areaAtuacao) {
		this.areaAtuacao = areaAtuacao;
	}

	public Long getMatricula() {
		return matricula;
	}

	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}

	public String getFormacao() {
		return formacao;
	}

	public void setFormacao(String formacao) {
		this.formacao = formacao;
	}

	public String getMestrado() {
		return mestrado;
	}

	public void setMestrado(String mestrado) {
		this.mestrado = mestrado;
	}

	public String getDoutorado() {
		return doutorado;
	}

	public void setDoutorado(String doutorado) {
		this.doutorado = doutorado;
	}

}
