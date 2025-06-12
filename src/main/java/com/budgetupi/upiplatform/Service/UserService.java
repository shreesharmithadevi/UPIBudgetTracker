package com.budgetupi.upiplatform.Service;

import com.budgetupi.upiplatform.Dto.CategoryDto;
import com.budgetupi.upiplatform.Model.Category;
import com.budgetupi.upiplatform.Model.User;
import com.budgetupi.upiplatform.Repository.CategoryRepository;
import com.budgetupi.upiplatform.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {

    private final UserRepository userRepo;
    private final CategoryRepository categoryRepo;
    public static User loggedInUser = null;

    public UserService(UserRepository userRepo, CategoryRepository categoryRepo) {
        this.userRepo = userRepo;
        this.categoryRepo = categoryRepo;
    }

    public User registerUser(User user) {

        if (userRepo.findByPhone(user.getPhone()).isPresent()) {
            throw new RuntimeException("Phone number already registered");
        }

        String upiId = user.getPhone() + "@" + user.getBankname() + ".upi";
        user.setUpiId(upiId);
        System.out.println("Generated UPI ID: " + upiId);
        return userRepo.save(user);
    }

    public boolean login(String username, String password) {
        Optional<User> user = userRepo.findByUpiId(username);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            loggedInUser = user.get();
            return true;
        }
        return false;
    }

    public Category addCategory(CategoryDto dto) {
        if (loggedInUser == null) {
            throw new RuntimeException("User not logged in");
        }
        boolean exists = categoryRepo.existsByNameAndUser(dto.getName(), loggedInUser);
        if (exists) {
            throw new RuntimeException("Category already exists");
        }

        double total = loggedInUser.getCategories().stream()
                .mapToDouble(Category::getBudgetLimit).sum();

        if (total + dto.getBudgetLimit() > loggedInUser.getSalary()) {
            throw new RuntimeException("Total budget exceeds salary");
        }

        Category category = new Category();
        category.setName(dto.getName());
        category.setBudgetLimit(dto.getBudgetLimit());
        category.setSpent(0.0);
        category.setUser(loggedInUser);

        return categoryRepo.save(category);
    }



    public void deleteCategory(String name, String categoryName) {
        if (loggedInUser == null) {
            throw new RuntimeException("User not logged in");
        }

        Category category = categoryRepo.findByNameAndUser(name, loggedInUser)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        categoryRepo.delete(category);
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }
}
