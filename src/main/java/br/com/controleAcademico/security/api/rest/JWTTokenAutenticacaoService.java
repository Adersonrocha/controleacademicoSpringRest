package br.com.controleAcademico.security.api.rest;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.com.controleAcademico.api.rest.ApplicationContextLoad;
import br.com.controleAcademico.model.api.rest.Usuario;
import br.com.controleAcademico.repository.api.rest.UsuarioRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
/**
 * Essa classe é responsavel por gerar e validar os Token JWT 
 * @author Anderson Rocha
 *
 */
@Service
@Component
public class JWTTokenAutenticacaoService {

	/**
	 * Essa váriavel estabelece a validade do token, expressa em milisegundos
	 * no nosso caso especificamos validade de 2 dias.
	 */
	private static final long EXPIRATION_TIME = 172800000;
	
	/**
	 * Senha unica para Auxiliar na segurança do TOKEN
	 */
	private static final String SECRET = "SenhaSuperSecreta";
	
	/**
	 * Prefixo padrao do TOKEN
	 */
	private static final String TOKEN_PREFIX ="Bearer";
	
	private static final String HEADER_STRING ="Authorization";
	
	/**
	 * Gerando o TOKEN de autenticacao, adicionando o cabeçalho e a resposta http
	 */
	public void addAuthetication(HttpServletResponse response, String username) throws IOException{
		
		/**
		 * Montagem do TOKEN
		 * Chama o gerador de TOKEN Jwts.builder()
		 * Adiciona o usuario setSubject(username)
		 * Tempo de expiração setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
		 * algoritmo de assinatura encapsula e compacta signWith(SignatureAlgorithm.HS512 , SECRET ).compact();
		 */
		String JWT = Jwts.builder().setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512 , SECRET ).compact();
	
		/**
		 * Junta Token com o prefixo
		 */
		String token = TOKEN_PREFIX +" "+ JWT; //ex: Bearer kjhasjdhjasdhkhiqw516843613654
	
		/**
		 * Adiciona no cabeçalho http
		 */
		response.addHeader(HEADER_STRING, token); //ex: Authorization: Bearer kjhasjdhjasdhkhiqw516843613654
		
		/**
		 * Escreve  token com resposta no corpo http
		 */
		response.getWriter().write("{\"Authorization\": \"" + token + "\"}");
									
	}
	
	/**
	 * Retorna usuario validado com o Token e caso não seja válido retorna null
	 */
	public Authentication getAuthentication(HttpServletRequest request) {
		
		/**
		 * Recupera o token enviado no cabeçalho http
		 */
		String token = request.getHeader(HEADER_STRING);
		
		if(token !=null) {
			String user = Jwts.parser().setSigningKey(SECRET) // recebe o token ex: Bearer kjhasjdhjasdhkhiqw516843613654
						.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))// retira o prefix, ex: kjhasjdhjasdhkhiqw516843613654
						.getBody().getSubject();					    //usuario final, ex: Joao Silva
		 if(user != null) {
			 Usuario usuario = ApplicationContextLoad.getApplicationContext()
					 		.getBean(UsuarioRepository.class).findUserByLogin(user);
			 
			 if(usuario != null) {
				 return new UsernamePasswordAuthenticationToken(
						 usuario.getLogin(), 
						 usuario.getSenha(), 
						 usuario.getAuthorities());
			 }
		 	}
		}
			return null; // Não autorizado
			
		}
	
	

}
