package br.com.delfos.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.delfos.services.ClienteServiceIMPL;
import br.com.delfos.services.EmpresaServiceIMPL;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private EmpresaServiceIMPL Eservico;
	
	@Autowired
	private ClienteServiceIMPL Cservico;
	
	
	@Bean
	 public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();

	}
	
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(Cservico)
            .passwordEncoder(passwordEncoder()).and().userDetailsService(Eservico).passwordEncoder(passwordEncoder());
    }

    
    
    @Override
	protected void configure(HttpSecurity http) throws Exception {

		http
		.formLogin()
		.defaultSuccessUrl("http://localhost:8080/swagger-ui/index.html#/")
		.permitAll()
		.and()
		.authorizeHttpRequests()
		.antMatchers("api/login/**").permitAll()
		.antMatchers("/login").permitAll()
		.anyRequest().authenticated()
		.and()
		.csrf().disable();
	}
    
    
}
