package br.com.controleAcademico.security.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import br.com.controleAcademico.service.api.rest.ImplementacaoUserDetailsService;

/** 
 * 
 * @author Anderson Rocha
 * Essa classe mapeia URL, enderecos, autoriza ou bloqueia acesso a URL
 */
@Configuration
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter{

	@Autowired
	private ImplementacaoUserDetailsService implementacaoUserDetailsService;

	/**
	 * Configura as solicitações de acesso por HTTP
	 */
	@Override
		protected void configure(HttpSecurity http) throws Exception {
			/**
			 * Ativando a proteção contra usuarios que não estão validados por token
			 */
			http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
			/**
			 * Ativando a permissao para acesso da pagina inicial do sistema
			 */
			.disable().authorizeRequests().antMatchers("/").permitAll()
										.antMatchers("/index").permitAll()
		     /**
		      * Quando o usuario fizer logout volta para o index
		      */
			 .anyRequest().authenticated().and().logout().logoutSuccessUrl("/index")
			 /**
			  * Mapeia Url de logout e insvalida usuario
			  */
			 .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			
			/**
			 * Filtra requisicao de login para autenticação
			 */
			.and().addFilterBefore(new JWTLoginFilter("/login", authenticationManager())
											, UsernamePasswordAuthenticationFilter.class)
			
			/**
			 * Filtra demais requisições para verificar a presença do token JWT
			 */
			.addFilterBefore(new JWTApiAutenticacaoFilter(), 
					UsernamePasswordAuthenticationFilter.class);
			
	}
	
	
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		/**
		 * Service que irá consultar o usuario no banco de dados
		 */
		auth.userDetailsService(implementacaoUserDetailsService)
		/** Padrao de codificação de senha*/
		.passwordEncoder(new BCryptPasswordEncoder());
	}
	

}
