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
import br.com.controleAcademico.model.api.rest.Disciplina;
import br.com.controleAcademico.repository.api.rest.DisciplinaRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/disciplina")
@Api(value = "API REST Disciplina")
@CrossOrigin(origins = "*")
public class DisciplinaController {

	
	@Autowired
	DisciplinaRepository disciplinaRepository;
	
	@GetMapping(value="/", produces = "application/json")
	@ApiOperation(value = "Esse metodo retorna todas as disciplinas Salvas no banco de dados")
	public ResponseEntity<List<Disciplina>> listarAlunos() {
		
		List<Disciplina> list = (List<Disciplina>) disciplinaRepository.findAll();
		return new ResponseEntity<List<Disciplina>>(list, HttpStatus.OK);
		
	}
	
	@GetMapping(value="/{id}", produces = "application/json")
	@ApiOperation(value = "Esse metodo retorna uma disciplinas atraves do ID ")
	public ResponseEntity<Disciplina> buscarPorId(@PathVariable (value = "id")Long id) throws ControleAcademicoNotFoundException{
		
		Optional<Disciplina> disciplina = disciplinaRepository.findById(id);
		return new ResponseEntity<Disciplina>(disciplina.get(),HttpStatus.OK);
	}
	
	@PostMapping(value="/", produces = "application/json")
	@ApiOperation(value = "Esse metodo cadastra novas disciplinas no banco de Dados")
	public ResponseEntity<Disciplina> cadastrar(@RequestBody Disciplina disciplina) throws ControleAcademicoAlreadyRegisteredException{
		Disciplina disciplinasalvo = disciplinaRepository.save(disciplina);
		return new ResponseEntity<Disciplina>(disciplinasalvo,HttpStatus.OK);
	}
	
	@PutMapping(value ="/", produces = "application/json")
	@ApiOperation(value = "Esse metodo atualiza as disciplinas no banco de Dados")
	public ResponseEntity<Disciplina> atualizar(@RequestBody Disciplina aluno) throws ControleAcademicoNotFoundException{
		
		 
		Disciplina disciplinaAtualizado = disciplinaRepository.save(aluno);
		return new ResponseEntity<Disciplina>(disciplinaAtualizado,HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{id}", produces= "application/text")
	@ApiOperation(value = "Esse metodo exclui uma disciplina A partir do seu ID ")
	public String delete(@PathVariable("id") Long id) throws ControleAcademicoNotFoundException{
		
		disciplinaRepository.deleteById(id);
		
		return "ok";
	}
	
	
}
