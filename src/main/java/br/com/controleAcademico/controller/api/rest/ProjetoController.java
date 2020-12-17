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
import br.com.controleAcademico.model.api.rest.Projeto;
import br.com.controleAcademico.repository.api.rest.ProfessorRepository;
import br.com.controleAcademico.repository.api.rest.ProjetoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/projeto")
@Api(value = "API REST projeto")
@CrossOrigin(origins = "*")
public class ProjetoController {

	@Autowired
	ProjetoRepository projetoRepository;
	
	@Autowired
	ProfessorRepository professorRepository;
	
	
	@GetMapping(value="/", produces = "application/json")
	@ApiOperation(value = "Esse metodo retorna todas as disciplinas Salvas no banco de dados")
	public ResponseEntity<List<Projeto>> listarProjetos() {
		
		List<Projeto> list = (List<Projeto>) projetoRepository.findAll();
		return new ResponseEntity<List<Projeto>>(list, HttpStatus.OK);
		
	}
	
	@GetMapping(value="/{id}", produces = "application/json")
	@ApiOperation(value = "Esse metodo retorna uma disciplinas atraves do ID ")
	public ResponseEntity<Projeto> buscarPorId(@PathVariable (value = "id")Long id) throws ControleAcademicoNotFoundException{
		
		Optional<Projeto> disciplina = projetoRepository.findById(id);
		return new ResponseEntity<Projeto>(disciplina.get(),HttpStatus.OK);
	}
	
	@PostMapping(value="/coordenador/{id}", produces = "application/json")
	@ApiOperation(value = "Esse metodo cadastra novas disciplinas no banco de Dados")
	public ResponseEntity<Projeto> cadastrar(@RequestBody Projeto disciplina, @PathVariable ("id") Long id) throws ControleAcademicoAlreadyRegisteredException{
	//	disciplina.setCoordenador(professorRepository.findById(id).get());
		Projeto disciplinasalvo = projetoRepository.save(disciplina);
		return new ResponseEntity<Projeto>(disciplinasalvo,HttpStatus.OK);
	}
	
	@PutMapping(value ="/", produces = "application/json")
	@ApiOperation(value = "Esse metodo atualiza as disciplinas no banco de Dados")
	public ResponseEntity<Projeto> atualizar(@RequestBody Projeto aluno) throws ControleAcademicoNotFoundException{
		
		 
		Projeto disciplinaAtualizado = projetoRepository.save(aluno);
		return new ResponseEntity<Projeto>(disciplinaAtualizado,HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{id}", produces= "application/text")
	@ApiOperation(value = "Esse metodo exclui uma disciplina A partir do seu ID ")
	public String delete(@PathVariable("id") Long id) throws ControleAcademicoNotFoundException{
		
		projetoRepository.deleteById(id);
		
		return "ok";
	}
	
	
}
