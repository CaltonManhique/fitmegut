package com.fitmegut.dciwarehousefinalproject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {


    private UserDetailsService userDetailsService;

    public void configurationGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        String[] staticResources = {
                "/css/**",
                "/images/**",
                "/fonts/**",
                "/scripts/**",
        };

        http.authorizeHttpRequests(authorize -> authorize
                                .requestMatchers("/").permitAll()
                                .requestMatchers("/donation/**").permitAll()
                                .requestMatchers("/exchange/**").permitAll()
                                .requestMatchers("/registration/**").permitAll()
                                .requestMatchers("/login/**").permitAll()
                                .requestMatchers("/custom-logout/signout").permitAll()
//                .requestMatchers("/registration/verify**").permitAll()
                .requestMatchers("/registration/passwordRecover/**").hasRole("MEMBER")
                                .requestMatchers(staticResources).permitAll()
                                .requestMatchers("/wardrobe/**").hasRole("MEMBER")
                                .anyRequest()
                                .authenticated()
                )
                .formLogin(form -> form.loginPage("/login")
                                .loginProcessingUrl("/authenticate")
                                .defaultSuccessUrl("/home", true)
                )
                .logout((logout) -> logout.logoutUrl("/logout")
                                .logoutSuccessUrl("/custom-logout/signout")
                        .permitAll()
                )
                .exceptionHandling(conf -> conf.accessDeniedPage("/unauthorized")
                );


        return http.build();
    }
}
