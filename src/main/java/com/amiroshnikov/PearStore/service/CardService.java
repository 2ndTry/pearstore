package com.amiroshnikov.PearStore.service;

import com.amiroshnikov.PearStore.dto.card.AddToCardDto;
import com.amiroshnikov.PearStore.dto.card.CardDto;
import com.amiroshnikov.PearStore.dto.card.CardItemDto;
import com.amiroshnikov.PearStore.exception.CustomException;
import com.amiroshnikov.PearStore.model.Card;
import com.amiroshnikov.PearStore.model.Product;
import com.amiroshnikov.PearStore.model.User;
import com.amiroshnikov.PearStore.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CardService {

    @Autowired
    private ProductService productService;
    @Autowired
    private CardRepository cardRepository;

    public void addToCard(AddToCardDto addToCardDto, User user) {

        Product product = productService.findById(addToCardDto.getProductId());

        Card card = new Card();
        card.setProduct(product);
        card.setUser(user);
        card.setQuantity(addToCardDto.getQuantity());
        card.setCreatedDate(new Date());

        cardRepository.save(card);
    }

    public CardDto listCardItems(User user) {

        List<Card> cardList = cardRepository.findAllByUserOrderByCreatedDateDesc(user);

        List<CardItemDto> cardItems = new ArrayList<>();
        double totalCoast = 0.0;
        for (Card card : cardList) {
            CardItemDto cardItemDto = new CardItemDto(card);
            totalCoast += cardItemDto.getQuantity() * card.getProduct().getPrice();
            cardItems.add(cardItemDto);
        }
        CardDto cardDto = new CardDto();
        cardDto.setTotalCoast(totalCoast);
        cardDto.setCardItemDtos(cardItems);

        return cardDto;
    }

    public void deleteCardItem(Long cardItemId, User user) {

        Optional<Card> optionalCard = cardRepository.findById(cardItemId);
        if (optionalCard.isEmpty()) {
            throw new CustomException(String.format("Card item with id = %s is invalid", cardItemId));
        }

        Card card = optionalCard.get();
        if (card.getUser() != user) {
            throw new CustomException(String.format("Card item does not belong to user: %s", cardItemId));
        }
        cardRepository.delete(card);
    }
}
