package com.budgetupi.upiplatform.Controller;

import com.budgetupi.upiplatform.Dto.CategoryDto;
import com.budgetupi.upiplatform.Dto.Login;
import com.budgetupi.upiplatform.Dto.UserDto;
import com.budgetupi.upiplatform.Dto.UserProfileDto;
import com.budgetupi.upiplatform.Model.User;
import com.budgetupi.upiplatform.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService service) {
        this.userService = service;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setAge(dto.getAge());
        user.setPhone(dto.getPhone());
        user.setBankname(dto.getBankName());
        user.setPassword(dto.getPassword());
        user.setConfirmpassword(dto.getConfirmpassword());
        user.setPin(dto.getPin());
        user.setSalary(dto.getSalary());
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Login dto) {
        boolean success = userService.login(dto.getUpiId(), dto.getPassword());
        if (success) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @GetMapping("/me")
    public ResponseEntity<?> getMe() {
        User user = userService.getLoggedInUser();
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No user is logged in");
        }
        List<CategoryDto> categoryDtos = new ArrayList<>();
        if (user.getCategories() != null) {
            categoryDtos = user.getCategories().stream()
                    .map(cat -> new CategoryDto(cat.getName(), cat.getBudgetLimit(), cat.getSpent()))
                    .collect(Collectors.toList());
        }
        UserProfileDto dto = new UserProfileDto( user.getName(), user.getUpiId(), user.getSalary(), categoryDtos);
        return ResponseEntity.ok(dto);
    }
}
