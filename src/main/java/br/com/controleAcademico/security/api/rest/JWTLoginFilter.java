package br.com.controleAcademico.security.api.rest;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.AntPathMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.controleAcademico.model.api.rest.Usuario;

/***
 * Estabelece o nosso gerenciador de token
 * @author Anderson Rocha
 *
 */

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter{
/**
 * Configurando gerenciador de autenticacao
 * @param url Obriga a autenticar a url
 * @param authenticationManager Gerenciador de token
 */
	protected JWTLoginFilter(String url, AuthenticationManager authenticationManager) {
		super(new AntPathRequestMatcher(url));
		
		
		setAuthenticationManager(authenticationManager);
	}
	
	/***
	 * 
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		
		Usuario user =new ObjectMapper().readValue(request.getInputStream(), Usuario.class);
		
		return getAuthenticationManager().
				authenticate(new UsernamePasswordAuthenticationToken(user.getLogin(),
						user.getSenha()));
	}

	/**
	 * 	
	 */
	@Override
		protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
				Authentication authResult) throws IOException, ServletException {
		
			
				
					new JWTTokenAutenticacaoService().addAuthetication(response, authResult.getName());
			
		}
	
}
