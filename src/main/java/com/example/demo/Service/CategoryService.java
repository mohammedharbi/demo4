package com.example.blogsystem.Service;

import com.example.blogsystem.ApiResponse.ApiException;
import com.example.blogsystem.Model.Category;
import com.example.blogsystem.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }
    public void addCategory(Category category) {
        categoryRepository.save(category);
    }
    public void updateCategory(Integer id,Category category) {
        Category oldCategory = categoryRepository.findCategoriesById(id);
        if (oldCategory != null) {
            oldCategory.setName(category.getName());
            categoryRepository.save(oldCategory);
        }else throw new ApiException("Category not found");
    }
    public void deleteCategory(Integer id) {
        Category category = categoryRepository.findCategoriesById(id);
        if (category != null) {
            categoryRepository.delete(category);
        }else throw new ApiException("Category not found");
    }
}
