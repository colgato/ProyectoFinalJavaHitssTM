package com.example.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.models.User;
import com.example.services.UserService;
import com.example.utils.UtilCrud;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "View all users", description = "Se muestran todos los usuarios")
    @GetMapping
    public List<User> list(){
        return userService.findAll();
    }

    @Operation(summary = "Create admin", description = "Se crea usuario de tipo admin")
    @PostMapping
        public ResponseEntity<?> create(@Valid @RequestBody User user, BindingResult result) {
        user.setAdmin(true);
        return handleCreateUser(user, result);
    }

    @Operation(summary = "Create new user", description = "Se crea un nuevo usuario")
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user, BindingResult result){
        user.setAdmin(false);
        return handleCreateUser(user, result);
    }

    private ResponseEntity<?> handleCreateUser(User user, BindingResult result) {
        if (result.hasFieldErrors()) {
            return UtilCrud.validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }
}
