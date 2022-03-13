package com.finhacker.smarket.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finhacker.smarket.util.msg.JsonMsg;
import com.finhacker.smarket.util.msg.MsgCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.PrintWriter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //TODO
        http.cors().and()
            .authorizeRequests()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .sessionManagement().and()
            .formLogin()
                .loginProcessingUrl("/login/api/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(((request, response, authentication) -> {
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    response.setCharacterEncoding("utf-8");
                    PrintWriter out = response.getWriter();
                    ObjectMapper objectMapper = new ObjectMapper();
                    out.write(objectMapper.writeValueAsString(new JsonMsg<>(true)));
                    out.flush();
                    out.close();
                }))
                .failureHandler((request, response, e) -> {
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    response.setCharacterEncoding("utf-8");
                    PrintWriter out = response.getWriter();
                    ObjectMapper objectMapper = new ObjectMapper();
                    out.write(objectMapper.writeValueAsString(new JsonMsg<Boolean>(MsgCode.USER_LOGIN_FAILED)));
                    out.flush();
                    out.close();
                })
                .permitAll().and()
            .logout()
                .logoutUrl("/logout")
                .permitAll()
            .and()
            .csrf().disable() //Debug
            ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

}
