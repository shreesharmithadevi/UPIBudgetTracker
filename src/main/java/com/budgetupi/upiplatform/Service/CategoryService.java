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
        if (user.getTotalSpent() + budgetLimit > user.getSalary()) {
            throw new RuntimeException("Category exceeds salary limit");
        }
        Category category = new Category();
        category.setName(name);
        category.setBudgetLimit(budgetLimit);
        category.setUser(user);
        user.setTotalSpent(user.getTotalSpent() + budgetLimit);
        userRepo.save(user);
        return categoryRepo.save(category);
    }

}