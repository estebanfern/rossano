package com.efernandez.rossano.bean;

import com.efernandez.rossano.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityBeans {

    private UserService userService;

    @Autowired
    public SecurityBeans(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth //.anyRequest().permitAll());
                        //Public endpoints
                        .requestMatchers(
                                "/css/**", "/img/**", "/js/**", "/error"
                        ).permitAll()
                        //Authenticated Endpoints
                        .requestMatchers(
                                "/"
                        ).authenticated()
                        //Permisos de manipulación de usuarios
                        .requestMatchers(
                                "/users"
                        ).hasAuthority("verUsuarios")
                        .requestMatchers(
                                "/users/save", "/users/save/*"
                        ).hasAuthority("crudUsuarios")

                ).formLogin(login -> login
                        .loginPage("/login")
                        .permitAll()
                ).logout(logout -> logout
                        .invalidateHttpSession(true)
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login?logoutSuccessfully")
                        .permitAll()
                );
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return userService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

}
