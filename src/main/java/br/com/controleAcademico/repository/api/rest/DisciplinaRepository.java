package br.com.controleAcademico.repository.api.rest;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.controleAcademico.model.api.rest.Disciplina;

@Repository
public interface DisciplinaRepository extends CrudRepository<Disciplina, Long> {

}
