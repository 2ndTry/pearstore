package com.amiroshnikov.PearStore.dto.card;

import javax.validation.constraints.NotNull;

public class AddToCardDto {

    private Long id;
    private @NotNull Long productId;
    private @NotNull Integer quantity;

    public AddToCardDto() {
    }

    public AddToCardDto(Long id, Long productId, Integer quantity) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
