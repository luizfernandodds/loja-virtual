package com.dev.loja_virtual.security;

import javax.servlet.http.HttpSessionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.dev.loja_virtual.service.ImplementationUserDetailsService;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebConfigSecurity extends WebSecurityConfigurerAdapter implements HttpSessionListener {
	
	@Autowired
	private ImplementationUserDetailsService implementationUserDetailsService;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

	    http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
	        .disable()
	        .authorizeRequests()
	            // Permite acesso livre ao actuator
	            .antMatchers("/loja/actuator/**").permitAll()

	            // Meus endpoints públicos
	            .antMatchers("/", "/index").permitAll()
	            .antMatchers("/pagamento/**", "/recuperarSenha", "/criaAcesso").permitAll()
	            .antMatchers("/resources/**", "/static/**", "/templates/**").permitAll()
	            .antMatchers("classpath:/static/**", "classpath:/resources/**", "classpath:/templates/**").permitAll()

	            .antMatchers(HttpMethod.POST, "/requisicaojunoboleto/**", "/notificacaoapiv2").permitAll()
	            .antMatchers(HttpMethod.GET, "/requisicaojunoboleto/**", "/notificacaoapiv2").permitAll()
	            .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()

	            // Tudo o que não foi explicitamente liberado precisa de autenticação
	            .anyRequest().authenticated()

	        // Logout
	        .and()
	            .logout().logoutSuccessUrl("/index")
	            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))

	        // JWT Filters
	        .and()
	            .addFilterAfter(new JWTLoginFilter("/login", authenticationManager()),
	                    UsernamePasswordAuthenticationFilter.class)
	            .addFilterBefore(new JwtApiAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	
	
	/*Irá consultar o user no banco com Spring Security*/
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(implementationUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
		
	}
	
	

	/* Ignora algumas URL livre de autenticação */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().
		    antMatchers(HttpMethod.GET, "/requisicaojunoboleto/**", "/notificacaoapiv2","/pagamento/**","/resources/**",
		    		"/static/**","/templates/**","classpath:/static/**","classpath:/resources/**",
		    		"classpath:/templates/**","/webjars/**","/WEB-INF/classes/static/**","/recuperarSenha","/criaAcesso")
		   .antMatchers(HttpMethod.POST,"/requisicaojunoboleto/**", "/notificacaoapiv2",
				   "/pagamento/**","/resources/**","/static/**","/templates/**",
				   "classpath:/static/**","classpath:/resources/**","classpath:/templates/**",
				   "/webjars/**","/WEB-INF/classes/static/**","/recuperarSenha","/criaAcesso");
		/* Ingnorando URL no momento para nao autenticar */
	}

}
