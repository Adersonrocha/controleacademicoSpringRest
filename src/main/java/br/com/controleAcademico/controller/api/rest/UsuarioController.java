package br.com.controleAcademico.controller.api.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.mapping.CrudMethodsSupportedHttpMethods;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.controleAcademico.exception.api.rest.ControleAcademicoAlreadyRegisteredException;
import br.com.controleAcademico.exception.api.rest.ControleAcademicoNotFoundException;
import br.com.controleAcademico.model.api.rest.Usuario;
import br.com.controleAcademico.repository.api.rest.UsuarioRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/usuario")
@Api(value = "API REST Usuario")
@CrossOrigin(origins = "*")
public class UsuarioController{

	@Autowired
	UsuarioRepository usuarioRepository;
	

	@GetMapping(value="/", produces = "application/json")
	@ApiOperation(value = "Esse metodo lista todos os usuarios no banco de Dados")
	public ResponseEntity<List<Usuario>> listarUsuarios() {
		
		List<Usuario> list = (List<Usuario>) usuarioRepository.findAll();
		return new ResponseEntity<List<Usuario>>(list, HttpStatus.OK);
		
	}
	
	@GetMapping(value="/{id}", produces = "application/json")
	@ApiOperation(value = "Esse metodo busca o usuarios pelo seu ID no banco de Dados")
	public ResponseEntity<Usuario> buscarPorId(@PathVariable (value = "id")Long id) throws ControleAcademicoNotFoundException{
		
		Optional<Usuario> aluno = usuarioRepository.findById(id);
		return new ResponseEntity<Usuario>(aluno.get(),HttpStatus.OK);
	}
	
	@PostMapping(value="/", produces = "application/json")
	@ApiOperation(value = "Esse metodo cadastra novos usuarios no banco de Dados")
	public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario aluno) throws ControleAcademicoAlreadyRegisteredException{
		
		aluno.setSenha(new BCryptPasswordEncoder().encode(aluno.getSenha()));
		
		
		Usuario alunosalvo = usuarioRepository.save(aluno);
		return new ResponseEntity<Usuario>(alunosalvo,HttpStatus.OK);
	}
	
	@PutMapping(value ="/", produces = "application/json")
	@ApiOperation(value = "Esse metodo atualiza os usuarios no banco de Dados")
	public ResponseEntity<Usuario> atualizar(@RequestBody Usuario aluno) throws ControleAcademicoNotFoundException{
		
	 
		Usuario alunoAtualizado = usuarioRepository.save(aluno);
		return new ResponseEntity<Usuario>(alunoAtualizado,HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{id}", produces= "application/text")
	@ApiOperation(value = "Esse metodo apaga os usuarios pelo ID ")
	public String delete(@PathVariable("id") Long id) throws ControleAcademicoNotFoundException{
		
		usuarioRepository.deleteById(id);
		
		return "ok";
	}
	
	
 }
