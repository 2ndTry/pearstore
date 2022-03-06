package com.amiroshnikov.PearStore.controller;

import com.amiroshnikov.PearStore.common.ApiResponse;
import com.amiroshnikov.PearStore.model.Category;
import com.amiroshnikov.PearStore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCategory(@RequestBody Category category) {
        categoryService.createCategory(category);
        return new ResponseEntity<>(new ApiResponse(true, "Create a new category"), HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public List<Category> listCategories() {
        return categoryService.listCategories();
    }

    @PutMapping("/update/{categoryId}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable("categoryId") Long categoryId, @RequestBody Category category) {
        if (!categoryService.findById(categoryId)) {
            return new ResponseEntity<>(new ApiResponse(false, "Category not found!"), HttpStatus.NOT_FOUND);
        } else {
            categoryService.editCategory(categoryId, category);
            return new ResponseEntity<>(new ApiResponse(true, "Update category"), HttpStatus.OK);
        }
    }
}
