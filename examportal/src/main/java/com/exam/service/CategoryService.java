package com.exam.service;

import com.exam.entities.exam.Category;

import java.util.Set;

public interface CategoryService {
    public Category addCategory(Category category);
    public Category updateCategory(Category category);
    public Set<Category> getAllCategories();
    public Category getCategoryById(Integer categoryId);
    public void deleteCategoryById(Integer categoryId);
}
