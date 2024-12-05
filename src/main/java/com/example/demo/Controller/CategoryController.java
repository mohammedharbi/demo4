package com.example.blogsystem.Controller;

import com.example.blogsystem.ApiResponse.ApiResponse;
import com.example.blogsystem.Model.Category;
import com.example.blogsystem.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/get")
    public ResponseEntity getAllCategory() {
        return ResponseEntity.ok(categoryService.getAllCategory());
    }
    @PostMapping("/add")
    public ResponseEntity addCategory(@RequestBody @Valid Category category, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors.getFieldError().getDefaultMessage());
        }
        categoryService.addCategory(category);
        return ResponseEntity.status(200).body(new ApiResponse("Category added successfully"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateCategory(@PathVariable Integer id, @RequestBody @Valid Category category, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors.getFieldError().getDefaultMessage());
        }
        categoryService.updateCategory(id, category);
        return ResponseEntity.status(200).body(new ApiResponse("Category updated successfully"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.status(200).body(new ApiResponse("Category deleted successfully"));
    }

}
