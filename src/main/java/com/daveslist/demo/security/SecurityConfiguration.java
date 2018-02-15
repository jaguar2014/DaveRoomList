package com.daveslist.demo.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("DaveWolf")
                .password("beastmaster").authorities("ADMIN");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/avilableroom/**","/detail/**").permitAll()
                .antMatchers("/check1","/check2").access("hasAnyAuthority('                                                                         ','USER')")
                .antMatchers("/","/addroom","/listadmin")



                /*.antMatchers("/").access("hasAuthority('USER') or hasAuthority('ADMIN')")
.antMatchers("/admin").access("hasAuthority('ADMIN')")*/
                .access("hasAuthority('ADMIN')")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .and()
                .httpBasic();

        http
                .csrf().disable();

        http
                .headers().frameOptions().disable();


    }
}
