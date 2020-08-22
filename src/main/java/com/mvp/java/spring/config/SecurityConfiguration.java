package com.mvp.java.spring.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * The Spring Security for the Application. At this time, only a in memory
 * hardcoded username and password is supported for a single user. 
 */
 @Configuration
 @EnableGlobalMethodSecurity
 public class SecurityConfiguration {
    private static final Logger LOG = LoggerFactory.getLogger(SecurityConfiguration.class);

        /**In Memory Authentication
         * @param authenticationManagerBuilder Allow to build an AuthenticationManager
         */
          
        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) {
            try {
                authenticationManagerBuilder.inMemoryAuthentication()
                        .withUser("all").password("all").roles("USER")
                        .and().withUser("admin").password("admin").roles("USER", "ADMIN");               
            } catch (Exception exception) {
                LOG.error("In Memory Authentication has failed", exception);
            }
        }
 }