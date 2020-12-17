package br.com.controleAcademico.model.api.rest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class Turma implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String nome;
	
	private String turno;
//	
//	@OneToOne
//	@JoinColumn(name = "professor")
//	private Professor professor;
//	
//	@OneToMany
//	@JoinColumn(name = "fk_disciplina")
//	private List<Disciplina> disciplinas = new ArrayList<Disciplina>();
//	
//	@OneToMany(mappedBy = "turma", cascade = CascadeType.ALL)
//	private List<Usuario> alunos = new ArrayList<Usuario>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

//	public Professor getProfessor() {
//		return professor;
//	}
//
//	public void setProfessor(Professor professor) {
//		this.professor = professor;
//	}
//
//	public List<Disciplina> getDisciplinas() {
//		return disciplinas;
//	}
//
//	public void setDisciplinas(List<Disciplina> disciplinas) {
//		this.disciplinas = disciplinas;
//	}

//	public List<Usuario> getAlunos() {
//		return alunos;
//	}
//
//	public void setAlunos(List<Usuario> alunos) {
//		this.alunos = alunos;
//	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}
	
	

}
