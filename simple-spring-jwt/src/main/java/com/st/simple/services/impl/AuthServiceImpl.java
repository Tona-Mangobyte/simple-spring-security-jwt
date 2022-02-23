package com.st.simple.services.impl;

import com.st.simple.entities.Session;
import com.st.simple.security.JwtTokenUtil;
import com.st.simple.services.AuthService;
import com.st.simple.services.SessionService;
import com.st.simple.validators.AuthValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private SessionService sessionService;

    public String authentication(AuthValidator auth) {
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(auth.getUsername());
            return authenticate(userDetails, auth.getUsername(), auth.getPassword());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    private String authenticate(UserDetails userDetails, String username, String password) throws Exception {
        try {
            Session session = new Session();
            session.setToken(jwtTokenUtil.generateToken(userDetails));
            session.setExpiredAt(LocalDate.now());
            return sessionService.createSession(session);
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
