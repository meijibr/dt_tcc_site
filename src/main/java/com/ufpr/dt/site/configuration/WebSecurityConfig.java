package com.ufpr.dt.site.configuration;

import com.ufpr.dt.site.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter  {

        @Autowired
        private AccessDeniedHandler accessDeniedHandler;

        @Autowired
        private UserDetailsServiceImpl userDetailsService;

        // roles admin allow to access /admin/**
        // roles user allow to access /user/**
        // custom 403 access denied handler
        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http.csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/", "/home", "/sobre", "/pessoas/registrar", "/pessoas", "/insere", "/vendor/**").permitAll()
                    .antMatchers("/admin/**").hasAnyRole("ADMIN")
                    .antMatchers("/user/**").hasAnyRole("USER")
//                    .anyRequest().authenticated()
                    .anyRequest().permitAll()
                    .and()
                    .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .and()
                    .logout()
                    .permitAll()
                    .and()
                    .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
        }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

//        @Autowired
//        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//
//            auth.inMemoryAuthentication()
//                    .withUser("user").password("oba123").roles("USER")
//                    .and()
//                    .withUser("admin").password("oba123").roles("ADMIN");
//        }
    }