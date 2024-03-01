package com.lms.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	SecurityFilterChain sfc(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable());
		http.cors(cor -> cor.configurationSource(new CorsConfigurationSource() {

			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

				List<String> listoforigin = List.of("*");
				List<String> listofmethods = List.of("GET", "POST", "PUT", "DELETE");
				CorsConfiguration cfg = new CorsConfiguration();

				cfg.setAllowedOrigins(listoforigin);
				cfg.setAllowedMethods(listofmethods);

				return cfg;

			}
		}));

		http.authorizeHttpRequests(

				auth ->

				{
					auth.requestMatchers("/user/testdb").authenticated();
					auth.requestMatchers("/save","/user/login").permitAll();

				});
		http.formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults());

		return http.build();
	}

	@Bean
	InMemoryUserDetailsManager imuds() {

		UserDetails user1 = User.withUsername("spring").password(pe().encode("123")).build();

		return new InMemoryUserDetailsManager(user1);
	}

	@Bean
	PasswordEncoder pe() {
		return new BCryptPasswordEncoder();
	}

}
