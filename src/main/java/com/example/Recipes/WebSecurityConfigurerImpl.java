package com.example.Recipes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfigurerImpl extends WebSecurityConfigurerAdapter {

    @Autowired
    UsersDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService) // user store 1
                .passwordEncoder(getEncoder());
//        auth
//                .inMemoryAuthentication()
//                .withUser("Admin").password("hardcoded").roles("USER")
////                .and().withUser("user1").password(getEncoder().encode("pass1")).roles()
////                .and().withUser("user2").password(getEncoder().encode("pass2")).roles("USER")
////                .and().withUser("user3").password(getEncoder().encode("pass3")).roles("ADMIN")
////                .and().withUser("A").password("pass1").roles()
////                .and()
////                .withUser("B").password("pass2").roles()
//                .and().passwordEncoder(NoOpPasswordEncoder.getInstance());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                .mvcMatchers(HttpMethod.POST, "/api/recipe/new").hasRole("ADMIN")
//                .mvcMatchers("/test").hasAnyRole("USER")
//                .mvcMatchers("/admin").hasRole("ADMIN")
//                .mvcMatchers("/user").hasAnyRole("ADMIN", "USER")
//                .mvcMatchers("/api/recipe/search").hasRole("ADMIN")
//                .mvcMatchers("/", "/public", "/register").permitAll()
//                .mvcMatchers("/register").permitAll()
                //.mvcMatchers("/**").permitAll()// or
                .antMatchers("/actuator/**").hasAuthority("MONITORING")
                .mvcMatchers("/api/register", "/actuator/shutdown").permitAll()
                .mvcMatchers("/api/register", "/actuator").permitAll()
                .anyRequest().fullyAuthenticated() // make remaining endpoints public (including POST /register)
                .and()
                .csrf().disable()
                .cors().disable()// disabling CSRF will allow sending POST request using Postman
                .httpBasic()
                .and()
                .headers().frameOptions().disable();
    }

    @Bean
    public PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }
}
