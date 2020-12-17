package br.com.controleAcademico.security.api.rest;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.web.filter.GenericFilterBean;
/***
 * Filtro onde recebe todas as requisições e autentica
 * @author Anderson Rocha
 *
 */
public class JWTApiAutenticacaoFilter extends GenericFilterBean{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		/***
		 * estabelece  a autenticacao para a requisicao
		 */
		
		Authentication authentication = new JWTTokenAutenticacaoService()
							.getAuthentication((HttpServletRequest)request);
		
		/* Coloca o processo de autenticação no Spring security */
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		/* Continua o processo */
	
		chain.doFilter(request, response);
		
	}

}
