package com.amiroshnikov.PearStore.service;

import com.amiroshnikov.PearStore.model.Category;
import com.amiroshnikov.PearStore.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void createCategory(Category category) {
        categoryRepository.save(category);
    }

    public List<Category> listCategories() {
        return categoryRepository.findAll();
    }

    public void editCategory(Long categoryId, Category editCategory) {
        Category category = categoryRepository.getById(categoryId);
        category.setCategoryName(editCategory.getCategoryName());
        category.setDescription(editCategory.getDescription());
        category.setImageUrl(editCategory.getImageUrl());
        categoryRepository.save(category);
    }

    public boolean findById(Long categoryId) {
        return categoryRepository.findById(categoryId).isPresent();
    }
}
