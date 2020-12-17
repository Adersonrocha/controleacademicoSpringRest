package br.com.controleAcademico.exception.api.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ControleAcademicoNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ControleAcademicoNotFoundException (String erro) {
		 super(String.format("Controle academico with name %s not found in the system.", erro));
	}
	
	public ControleAcademicoNotFoundException(Long id) {
        super(String.format("Controle Academico with id %s not found in the system.", id));
    }

}
