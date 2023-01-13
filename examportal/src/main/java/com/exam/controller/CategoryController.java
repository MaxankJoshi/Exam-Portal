package com.exam.controller;

import com.exam.entities.exam.Category;
import com.exam.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/category/api")
@CrossOrigin("*")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category createdCategory = this.categoryService.addCategory(category);

        return new ResponseEntity<Category>(createdCategory, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category) {
        Category updatedCategory = this.categoryService.updateCategory(category);

        return new ResponseEntity<Category>(updatedCategory,HttpStatus.OK);
    }

    @GetMapping("/get/{categoryId}")
    public ResponseEntity<Category> getCategory(@PathVariable("categoryId") Integer categoryId) {
        Category category = this.categoryService.getCategoryById(categoryId);

        return new ResponseEntity<Category>(category,HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<Set<Category>> getAllCategories() {
        Set<Category> allCategories = this.categoryService.getAllCategories();

        return new ResponseEntity<Set<Category>>(allCategories,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable("categoryId") Integer categoryId) {
        this.categoryService.deleteCategoryById(categoryId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
