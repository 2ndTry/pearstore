package com.amiroshnikov.PearStore.repository;

import com.amiroshnikov.PearStore.model.User;
import com.amiroshnikov.PearStore.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {

    List<WishList> findAllByUserOrderByCreatedDateDesc(User user);
}
