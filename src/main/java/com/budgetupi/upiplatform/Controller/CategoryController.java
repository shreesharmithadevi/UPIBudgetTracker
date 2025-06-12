package com.budgetupi.upiplatform.Controller;

import com.budgetupi.upiplatform.Dto.CategoryDto;
import com.budgetupi.upiplatform.Dto.SpendDto;
import com.budgetupi.upiplatform.Service.CategoryService;
import com.budgetupi.upiplatform.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;
    private final UserService userService;

    public CategoryController(CategoryService categoryService, UserService userService) {
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @PostMapping("/category/add")
    public ResponseEntity<String> addCategory(@RequestBody CategoryDto categoryDto) {
        try {
            userService.addCategory(categoryDto);
            return ResponseEntity.ok("Category added successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/spend")
    public ResponseEntity<String> spendCategory(@RequestBody SpendDto request) {
        try {
            categoryService.spendCategory(request);
            return ResponseEntity.ok("Payment successful");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/category/delete/{upiId}/{categoryName}")
    public ResponseEntity<String> deleteCategory(@PathVariable String upiId, @PathVariable String categoryName) {
        try {
            userService.deleteCategory(upiId, categoryName);
            return ResponseEntity.ok("Category deleted successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
