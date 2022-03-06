package com.amiroshnikov.PearStore.controller;

import com.amiroshnikov.PearStore.common.ApiResponse;
import com.amiroshnikov.PearStore.dto.ProductDto;
import com.amiroshnikov.PearStore.model.Product;
import com.amiroshnikov.PearStore.model.User;
import com.amiroshnikov.PearStore.model.WishList;
import com.amiroshnikov.PearStore.service.AuthenticationService;
import com.amiroshnikov.PearStore.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishListController {

    @Autowired
    private WishListService wishListService;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToWishList(@RequestBody Product product,
                                                     @RequestParam("token") String token) {

        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        WishList wishList = new WishList(user, product);
        wishListService.createWishList(wishList);

        ApiResponse apiResponse = new ApiResponse(true, "Added to wishlist");

        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{token}")
    public ResponseEntity<List<ProductDto>> getWishList(@PathVariable("token") String token) {

        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);

        List<ProductDto> productDtos = wishListService.getWishListForUser(user);

        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }
}
