package com.amiroshnikov.PearStore.controller;

import com.amiroshnikov.PearStore.common.ApiResponse;
import com.amiroshnikov.PearStore.dto.card.AddToCardDto;
import com.amiroshnikov.PearStore.dto.card.CardDto;
import com.amiroshnikov.PearStore.model.User;
import com.amiroshnikov.PearStore.service.AuthenticationService;
import com.amiroshnikov.PearStore.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CardController {

    @Autowired
    private CardService cardService;
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToCard(@RequestBody AddToCardDto addToCardDto,
                                                 @RequestParam("token") String token) {
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);

        cardService.addToCard(addToCardDto, user);

        return new ResponseEntity<>(new ApiResponse(true, "Added to card"), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<CardDto> getCardItems(@RequestParam("token") String token) {

        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);

        CardDto cardDto = cardService.listCardItems(user);
        return new ResponseEntity<>(cardDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{cardItemId}")
    public ResponseEntity<ApiResponse> deleteCardItem(@PathVariable("cardItemId") Long itemId,
                                                      @RequestParam("token") String token) {

        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);

        cardService.deleteCardItem(itemId, user);

        return new ResponseEntity<>(new ApiResponse(true, "Item has been removed"), HttpStatus.OK);
    }
}
