package com.budgetupi.upiplatform.Service;

import com.budgetupi.upiplatform.Model.Category;
import com.budgetupi.upiplatform.Model.User;
import com.budgetupi.upiplatform.Repository.CategoryRepository;
import com.budgetupi.upiplatform.Repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepo;
    private final UserRepository userRepo;
    public CategoryService(CategoryRepository repo, UserRepository userRepo) {
        this.categoryRepo = repo;
        this.userRepo = userRepo;
    }

    public Category addCategory(User user, String name, double budgetLimit) {
        double currentCategoryBudgetSum = user.getCategories().stream()
                .mapToDouble(Category::getBudgetLimit).sum();

        if (currentCategoryBudgetSum + budgetLimit > user.getSalary()) {
            throw new IllegalArgumentException("Total category budget exceeds your salary.");
        }

        Category category = new Category();
        category.setName(name);
        category.setBudgetLimit(budgetLimit);
        category.setSpent(0.0);
        category.setUser(user);

        return categoryRepo.save(category);
    }
    public Category spendCategory(User user, Long categoryId, double amount) {
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        if (category.getSpent() + amount > category.getBudgetLimit()) {
            throw new RuntimeException("Insufficient category budget");
        }

        if (user.getTotalSpent() + amount > user.getSalary()) {
            throw new RuntimeException("Insufficient total balance");
        }
        category.setSpent(category.getSpent() + amount);
        user.setTotalSpent(user.getTotalSpent() + amount);
        userRepo.save(user);
        return categoryRepo.save(category);
    }


}