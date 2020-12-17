package br.com.controleAcademico.repository.api.rest;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.controleAcademico.model.api.rest.Turma;

@Repository
public interface TurmaRepository extends CrudRepository<Turma, Long> {

}
