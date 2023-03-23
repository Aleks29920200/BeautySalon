package com.example.dealership1.config;

import com.example.dealership1.repositories.UserRepo;
import com.example.dealership1.services.HairSalonUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

@Configuration
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().disable()
                .csrf().disable().authorizeRequests()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .requestMatchers("/index","/users/login", "/users/register","/index?language").anonymous()
                .requestMatchers("/home").authenticated()
                .requestMatchers("/logout").authenticated()
                .requestMatchers("/profile").authenticated()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/boss/**").hasRole("BOSS")
                .requestMatchers("/user/**").hasRole("USER")
                .and()
                .formLogin()
                .loginPage("/users/login")
                .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
                .defaultSuccessUrl("/home")
                .failureUrl("/users/login?error=true")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/").
                clearAuthentication(true);
        return httpSecurity.build();
    }
    @Bean
    public UserDetailsService userDetailsService(UserRepo userRepository) {
        return new HairSalonUserDetailsService(userRepository);
    }
}
