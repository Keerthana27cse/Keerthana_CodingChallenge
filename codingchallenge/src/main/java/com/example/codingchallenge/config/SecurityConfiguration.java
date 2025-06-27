package com.example.codingchallenge.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity security)throws Exception
	{
		security
		.csrf(csrf -> csrf.disable()) //http.csrf().disable();
		.authorizeHttpRequests//cust<t>
		(requests->requests
		.requestMatchers("/api/tasks/**").permitAll()
		.requestMatchers("/api/admin/tasks/**").hasRole("ADMIN")
		.requestMatchers("/api/user/tasks/**").hasRole("USER")
		.anyRequest()//other than http req authenticated
		.authenticated()
		)
		.formLogin(Customizer.withDefaults());//form Login
		//.logout(Customizer.withDefaults());
		return security.build();		
	}
	
	
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
	
	}
	
}

