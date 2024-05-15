package com.linktic.ecommerce.backend.infrastructure.controller;

import com.linktic.ecommerce.backend.application.UserService;
import com.linktic.ecommerce.backend.domain.model.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public User save(@RequestBody User user){
        return userService.save(user);
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable Integer id){
        return userService.findById(id);
    }
}
