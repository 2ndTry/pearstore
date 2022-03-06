package com.amiroshnikov.PearStore.service;

import com.amiroshnikov.PearStore.dto.ProductDto;
import com.amiroshnikov.PearStore.exception.ProductNotExistsException;
import com.amiroshnikov.PearStore.model.Category;
import com.amiroshnikov.PearStore.model.Product;
import com.amiroshnikov.PearStore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void createProduct(ProductDto productDto, Category category) {
        Product product = new Product();
        product.setDescription(productDto.getDescription());
        product.setImageUrl(productDto.getImageUrl());
        product.setName(productDto.getName());
        product.setCategory(category);
        product.setPrice(productDto.getPrice());
        productRepository.save(product);
    }

    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        for(Product product : products) {
            productDtos.add(getProductDto(product));
        }
        return productDtos;
    }

    public ProductDto getProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setDescription(product.getDescription());
        productDto.setImageUrl(product.getImageUrl());
        productDto.setName(product.getName());
        productDto.setCategoryId(product.getCategory().getId());
        productDto.setPrice(product.getPrice());
        productDto.setId(product.getId());
        return productDto;
    }

    public void updateProduct(ProductDto productDto, Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isEmpty()) {
            throw new EntityNotFoundException("Product not found");
        }
        Product product = optionalProduct.get();
        product.setDescription(productDto.getDescription());
        product.setImageUrl(productDto.getImageUrl());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        productRepository.save(product);
    }

    public Product findById(Long productId) {
        Optional<Product> optionProduct = productRepository.findById(productId);
        if(optionProduct.isEmpty()) {
            throw new ProductNotExistsException(String.format("Product with id = %S is invalid", productId));
        }
        return optionProduct.get();
    }
}
