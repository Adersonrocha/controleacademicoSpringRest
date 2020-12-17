package br.com.controleAcademico.model.api.rest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ConstraintMode;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.UniqueConstraint;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@DiscriminatorValue("U")
@SequenceGenerator(name = "seq_user", sequenceName = "seq_user", allocationSize = 1, initialValue = 1)
public class Usuario implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_user")
	private Long id;

	private String nome;

	private String sobrenome;

	private String login;

	protected String senha;

	private Long matricula;

	@Enumerated(EnumType.STRING)
	private Funcao funcao;

	private String formacao;

	private String mestrado;

	private String doutorado;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "usuarios_role", uniqueConstraints = @UniqueConstraint(columnNames = { "usuario_id",
			"role_id" }, name = "unique_role_user"), joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id", unique = false, foreignKey = @ForeignKey(name = "usuario_fk", value = ConstraintMode.CONSTRAINT)), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id", table = "role", unique = false, updatable = false, foreignKey = @ForeignKey(name = "role_fk", value = ConstraintMode.CONSTRAINT)))
	private List<Role> role = new ArrayList<>();

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "usuario_projeto", joinColumns = { @JoinColumn(name = "usuario_id") }, inverseJoinColumns = {
			@JoinColumn(name = "projeto_id") })
	private List<Projeto> projeto = new ArrayList<Projeto>();

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

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	// @JsonIgnore
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Long getMatricula() {
		return matricula;
	}

	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}

	public Funcao getFuncao() {
		return funcao;
	}

	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}

	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return role;
	}

	@Override
	@JsonIgnore
	public String getPassword() {

		return this.senha;
	}

	@Override
	@JsonIgnore
	public String getUsername() {

		return this.login;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	@JsonIgnore
	public boolean isEnabled() {

		return true;
	}

	public void setFormacao(String formacao) {
		this.formacao = formacao;
	}

	public String getFormacao() {
		return formacao;
	}

	public void setMestrado(String mestrado) {
		this.mestrado = mestrado;
	}

	public String getMestrado() {
		return mestrado;
	}

	public void setDoutorado(String doutorado) {
		this.doutorado = doutorado;
	}

	public String getDoutorado() {
		return doutorado;
	}

	public void setProjeto(List<Projeto> projeto) {
		this.projeto = projeto;
	}

	public List<Projeto> getProjeto() {
		return projeto;
	}
}
