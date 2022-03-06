package com.amiroshnikov.PearStore.dto.card;

import com.amiroshnikov.PearStore.model.Card;
import com.amiroshnikov.PearStore.model.Product;

public class CardItemDto {

    private Long id;
    private Integer quantity;
    private Product product;

    public CardItemDto() {
    }

    public CardItemDto(Long id, Integer quantity, Product product) {
        this.id = id;
        this.quantity = quantity;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public CardItemDto(Card card) {
        this.id = card.getId();
        this.quantity = card.getQuantity();
        this.setProduct(card.getProduct());
    }
}
