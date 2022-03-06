package com.amiroshnikov.PearStore.service;

import com.amiroshnikov.PearStore.exception.AuthenticationException;
import com.amiroshnikov.PearStore.model.AuthenticationToken;
import com.amiroshnikov.PearStore.model.User;
import com.amiroshnikov.PearStore.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthenticationService {

    @Autowired
    private TokenRepository tokenRepository;

    public void saveConfirmationToken(AuthenticationToken authenticationToken) {
        tokenRepository.save(authenticationToken);
    }

    public AuthenticationToken getToken(User user) {
        return tokenRepository.findByUser(user);
    }

    public User getUser(String token) {
        AuthenticationToken authenticationToken = tokenRepository.findByToken(token);
        if(Objects.isNull(authenticationToken)) {
            return null;
        }
        return authenticationToken.getUser();
    }

    public void authenticate(String token) {
        if(Objects.isNull(token)) {
            throw new AuthenticationException("Token not found");
        }
        if(Objects.isNull(getUser(token))) {
            throw new AuthenticationException("Token is not valid");
        }
    }
}
