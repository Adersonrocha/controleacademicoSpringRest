package br.com.controleAcademico.exception.api.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ControleAcademicoAlreadyRegisteredException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ControleAcademicoAlreadyRegisteredException(String erro) {
		 super(String.format("Controle academico with name %s already registered in the system.", erro));
	}

}
