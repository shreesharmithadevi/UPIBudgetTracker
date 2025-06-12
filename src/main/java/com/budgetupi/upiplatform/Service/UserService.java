package com.budgetupi.upiplatform.Service;

import com.budgetupi.upiplatform.Model.User;
import com.budgetupi.upiplatform.Repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;
@Service
public class UserService {

    private final UserRepository userRepo;
    private User loggedInUser = null;

    public UserService(UserRepository repo) {
        this.userRepo = repo;
    }

    public User registeredUser(User user) {
        if (userRepo.findByPhone(user.getPhone()).isEmpty()) {
            String upiId = user.getPhone() + "@" + user.getBankname() + ".upi";
            user.setUpiId(upiId);
            System.out.println("Generated Username (UPI ID): " + upiId);
            return userRepo.save(user);
        } else {
            throw new RuntimeException("Phone number already registered");
        }
    }

    public boolean login(String username, String password) {
        Optional<User> user = userRepo.findByUpiId(username);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            loggedInUser = user.get();
            return true;
        }
        return false;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }
}
