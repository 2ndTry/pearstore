package com.amiroshnikov.PearStore.repository;

import com.amiroshnikov.PearStore.model.AuthenticationToken;
import com.amiroshnikov.PearStore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<AuthenticationToken, Long> {

    AuthenticationToken findByUser(User user);
    AuthenticationToken findByToken(String token);
}
