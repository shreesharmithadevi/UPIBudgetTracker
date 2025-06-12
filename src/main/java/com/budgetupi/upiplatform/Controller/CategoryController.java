package com.budgetupi.upiplatform.Controller;

import com.budgetupi.upiplatform.Dto.CategoryDto;
import com.budgetupi.upiplatform.Model.Category;
import com.budgetupi.upiplatform.Model.User;
import com.budgetupi.upiplatform.Service.CategoryService;
import com.budgetupi.upiplatform.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;
    private final UserService userService;

    public CategoryController(CategoryService categoryService, UserService userService) {
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<Category> addCategory(@RequestBody CategoryDto dto) {
        User user = userService.getLoggedInUser();
        Category newCategory = categoryService.addCategory(user, dto.getName(), dto.getBudgetLimit());
        return ResponseEntity.ok(newCategory);
    }
}
