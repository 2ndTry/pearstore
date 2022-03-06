package com.amiroshnikov.PearStore.controller;

import com.amiroshnikov.PearStore.common.ApiResponse;
import com.amiroshnikov.PearStore.dto.ProductDto;
import com.amiroshnikov.PearStore.model.Category;
import com.amiroshnikov.PearStore.repository.CategoryRepository;
import com.amiroshnikov.PearStore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/")
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<ProductDto> productDtos = productService.getAllProducts();
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDto productDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (optionalCategory.isEmpty()) {
            return new ResponseEntity<>(new ApiResponse(false, "Category dosn't exist"),
                    HttpStatus.BAD_REQUEST);
        }
        productService.createProduct(productDto, optionalCategory.get());
        return new ResponseEntity<>(new ApiResponse(true, "Product created"),
                HttpStatus.CREATED);
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<ApiResponse> updateProduct(
                                    @PathVariable("productId") Long productId,
                                    @RequestBody ProductDto productDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (optionalCategory.isEmpty()) {
            return new ResponseEntity<>(new ApiResponse(false, "Category dosn't exist"),
                    HttpStatus.BAD_REQUEST);
        }
        productService.updateProduct(productDto, productId);
        return new ResponseEntity<>(new ApiResponse(true, "Product updated"),
                HttpStatus.OK);
    }
}
