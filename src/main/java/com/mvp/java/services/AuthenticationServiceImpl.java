/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvp.java.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationServiceImpl.class);
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationServiceImpl(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**
     * Authenticates the Authentication toekn past in.
     * @param proposedAuthenticationToken contains username and password to authenticate
     * @return Authentication object
     * @throws AuthenticationException if authentication fails (a runtime exception)
     */
    @Override
    public Authentication authenticate(Authentication proposedAuthenticationToken) throws AuthenticationException{
        Authentication authenticatedToken = authenticationManager.authenticate(proposedAuthenticationToken);
        LOG.info("User< {} > has been authenticated successfully", authenticatedToken.getName());
        return authenticatedToken;
    }

}
