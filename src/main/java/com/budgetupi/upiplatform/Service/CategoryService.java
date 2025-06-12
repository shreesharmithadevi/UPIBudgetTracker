package com.budgetupi.upiplatform.Service;

import com.budgetupi.upiplatform.Dto.SpendDto;
import com.budgetupi.upiplatform.Model.Category;
import com.budgetupi.upiplatform.Model.User;
import com.budgetupi.upiplatform.Repository.CategoryRepository;
import com.budgetupi.upiplatform.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepo;
    private final UserRepository userRepo;

    public CategoryService(CategoryRepository categoryRepo, UserRepository userRepo) {
        this.categoryRepo = categoryRepo;
        this.userRepo = userRepo;
    }

    public Category addCategory(String upiId, String name, double budgetLimit) {
        User user = userRepo.findByUpiId(upiId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        boolean exists = categoryRepo.existsByNameAndUser(name, user);
        if (exists) {
            throw new IllegalArgumentException("Category already exists for this user");
        }

        double currentTotal = user.getCategories().stream()
                .mapToDouble(Category::getBudgetLimit).sum();

        if (currentTotal + budgetLimit > user.getSalary()) {
            throw new IllegalArgumentException("Total category budget exceeds your salary.");
        }

        Category category = new Category(name, budgetLimit, user);
        category.setSpent(0.0);
        return categoryRepo.save(category);
    }

    @Transactional
    public void spendCategory(SpendDto request) {
        User user = userRepo.findByUpiId(request.getUpiId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!user.getPin().equals(request.getPin())) {
            throw new IllegalArgumentException("Invalid UPI PIN");
        }

        Category category = categoryRepo.findByNameAndUser(request.getName(), user)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        double remaining = category.getBudgetLimit() - category.getSpent();
        if (request.getSpend() > remaining) {
            throw new IllegalArgumentException("Insufficient category budget");
        }

        if (request.getSpend() > user.getSalary()) {
            throw new IllegalArgumentException("Insufficient total balance");
        }

        category.setSpent(category.getSpent() + request.getSpend());
        user.setSalary(user.getSalary() - request.getSpend());

        categoryRepo.save(category);
        userRepo.save(user);
    }

    // Edit category budget
    public Category updateCategory(String upiId, String categoryName, double newBudget) {
        User user = userRepo.findByUpiId(upiId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Category category = categoryRepo.findByNameAndUser(categoryName, user)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        double totalExcludingCurrent = user.getCategories().stream()
                .filter(cat -> !cat.getName().equals(categoryName))
                .mapToDouble(Category::getBudgetLimit)
                .sum();

        if (totalExcludingCurrent + newBudget > user.getSalary()) {
            throw new IllegalArgumentException("Updated category budget exceeds salary");
        }

        category.setBudgetLimit(newBudget);
        return categoryRepo.save(category);
    }

    public void deleteCategory(String upiId, String categoryName) {
        User user = userRepo.findByUpiId(upiId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Category category = categoryRepo.findByNameAndUser(categoryName, user)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        categoryRepo.delete(category);
    }
}
