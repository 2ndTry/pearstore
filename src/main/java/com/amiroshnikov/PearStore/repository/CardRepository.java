package com.amiroshnikov.PearStore.repository;

import com.amiroshnikov.PearStore.model.Card;
import com.amiroshnikov.PearStore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findAllByUserOrderByCreatedDateDesc(User user);
}
