package com.exterro.ForumDemo.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.exterro.ForumDemo.services.JpaUserDetailsService;



@Configuration
@EnableWebSecurity
public class ForumConfiguration {
	
	private final JpaUserDetailsService jpaUserDetailsService;
		
	public ForumConfiguration(JpaUserDetailsService jpaUserDetailsService) {
		super();
		this.jpaUserDetailsService = jpaUserDetailsService;
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.csrf(csrf -> csrf.ignoringRequestMatchers("/index/**","/register/"))
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/index/**").permitAll()
						.requestMatchers("/register/").permitAll()
						.requestMatchers("/home/**").hasAnyRole("USER","ADMIN")
						.requestMatchers("/admin/**").hasAnyRole("ADMIN")
						.anyRequest().authenticated())
				.userDetailsService(jpaUserDetailsService)
				.formLogin().and().build();
				
	}
	
	@Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}