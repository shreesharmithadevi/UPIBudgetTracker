package com.budgetupi.upiplatform.Controller;

import com.budgetupi.upiplatform.Dto.Login;
import com.budgetupi.upiplatform.Dto.UserDto;
import com.budgetupi.upiplatform.Model.User;
import com.budgetupi.upiplatform.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        user.setConfrimpassword(dto.getConfrimpassword());
        user.setPin(dto.getPin());
        user.setSalary(dto.getSalary());
        return ResponseEntity.ok(userService.registeredUser(user));
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
    public ResponseEntity<User> getMe() {
        User user = userService.getLoggedInUser();
        return ResponseEntity.ok(user);
    }

}
