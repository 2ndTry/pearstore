package com.amiroshnikov.PearStore.dto.card;

import java.util.List;

public class CardDto {

    private List<CardItemDto> cardItemDtos;
    private double totalCoast;

    public CardDto() {
    }

    public CardDto(List<CardItemDto> cardItemDtos, double totalCoast) {
        this.cardItemDtos = cardItemDtos;
        this.totalCoast = totalCoast;
    }

    public List<CardItemDto> getCardItemDtos() {
        return cardItemDtos;
    }

    public void setCardItemDtos(List<CardItemDto> cardItemDtos) {
        this.cardItemDtos = cardItemDtos;
    }

    public double getTotalCoast() {
        return totalCoast;
    }

    public void setTotalCoast(double totalCoast) {
        this.totalCoast = totalCoast;
    }
}
