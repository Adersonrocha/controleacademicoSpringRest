package br.com.controleAcademico.controller.api.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import br.com.controleAcademico.model.api.rest.Professor;
import br.com.controleAcademico.repository.api.rest.ProfessorRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(value = "/professor")
@Api(value = "API REST Professor")
@CrossOrigin(origins = "*")
public class ProfessorController {

	@Autowired
	ProfessorRepository professorRepository;
	
	
	@GetMapping(value="/", produces = "application/json")
	@ApiOperation(value = "Esse metodo lista os professores que estão cadastrado no banco de Dados")
	public ResponseEntity<List<Professor>> listarAlunos(){
		
		List<Professor> list = (List<Professor>) professorRepository.findAll();
		return new ResponseEntity<List<Professor>>(list, HttpStatus.OK);
		
	}
	
	@GetMapping(value="/{id}", produces = "application/json")
	@ApiOperation(value = "Esse metodo cadastra listas os professores no banco de Dados")
	public ResponseEntity<Professor> buscarPorId(@PathVariable (value = "id")Long id) throws ControleAcademicoNotFoundException{
		
		Optional<Professor> professor = professorRepository.findById(id);
		return new ResponseEntity<Professor>(professor.get(),HttpStatus.OK);
	}
	
	@PostMapping(value="/", produces = "application/json")
	@ApiOperation(value = "Esse metodo cadastrar novos professores no banco de Dados")
	public ResponseEntity<Professor> cadastrar(@RequestBody Professor professor) throws ControleAcademicoAlreadyRegisteredException{
		Professor professorsalvo = professorRepository.save(professor);
		return new ResponseEntity<Professor>(professorsalvo,HttpStatus.OK);
	}
	
	@PutMapping(value ="/", produces = "application/json")
	@ApiOperation(value = "Esse metodo atualizar os professores no banco de Dados")
	public ResponseEntity<Professor> atualizar(@RequestBody Professor professor) throws ControleAcademicoNotFoundException{
		Professor alunoAtualizado = professorRepository.save(professor);
		return new ResponseEntity<Professor>(alunoAtualizado,HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{id}", produces= "application/text")
	@ApiOperation(value = "Esse metodo deleta os professores do banco de Dados pelo ID")
	public String delete(@PathVariable("id") Long id) throws ControleAcademicoNotFoundException{
		
		professorRepository.deleteById(id);
		
		return "ok";
	}
	
	
	
}
