package com.amiroshnikov.PearStore.service;

import com.amiroshnikov.PearStore.dto.ProductDto;
import com.amiroshnikov.PearStore.model.User;
import com.amiroshnikov.PearStore.model.WishList;
import com.amiroshnikov.PearStore.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishListService {

    private final WishListRepository wishListRepository;
    private final ProductService productService;

    @Autowired
    public WishListService(WishListRepository wishListRepository, ProductService productService) {
        this.wishListRepository = wishListRepository;
        this.productService = productService;
    }

    public void createWishList(WishList wishList) {
        wishListRepository.save(wishList);
    }

    public List<ProductDto> getWishListForUser(User user) {
        List<WishList> wishLists = wishListRepository.findAllByUserOrderByCreatedDateDesc(user);
        List<ProductDto> productDtos = new ArrayList<>();

        for (WishList wishList : wishLists) {
            productDtos.add(productService.getProductDto(wishList.getProduct()));
        }

        return productDtos;
    }
}
