package br.com.controleAcademico.repository.api.rest;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.controleAcademico.model.api.rest.Projeto;

@Repository
@Transactional
public interface ProjetoRepository extends CrudRepository<Projeto, Long> {

	
	
	
}
