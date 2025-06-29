package com.example.codingchallenge.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Autowired UserDetailsService userDetailsService;
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity security)throws Exception
	{
		security
		.csrf(csrf -> csrf.disable()) //http.csrf().disable();
		.authorizeHttpRequests//cust<t>
		(requests->requests
		.requestMatchers("/user").permitAll()
		.requestMatchers("/userspi/admin/tasks/**").hasRole("ADMIN")
		.requestMatchers("/api/user/tasks/**").hasRole("USER")
		.anyRequest()//other than http req authenticated
		.authenticated())
		.httpBasic(Customizer.withDefaults());
	    return security.build();
	}
	@Bean
	public AuthenticationProvider authenticationProvider()
	{
		DaoAuthenticationProvider provider= new DaoAuthenticationProvider();
		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());//no encode
		provider.setUserDetailsService(userDetailsService);
		return provider;
	}
	
}

		//form Login
		//.logout(Customizer.withDefaults());
	
	
	
	/*
	@Bean
	public UserDetailsService userDetailsService()
	{
		UserDetails userKeeri=User.withDefaultPasswordEncoder()
				.username("keerii")
				.password("keeri123")
				.roles("USER","ADMIN")
				.build();
	
		UserDetails userRam=User.withDefaultPasswordEncoder()
				.username("ram")
				.roles("ADMIN")
				.password("ram123")
				.build();
		return new InMemoryUserDetailsManager(userRam,userKeeri);
	
	}*/
	

