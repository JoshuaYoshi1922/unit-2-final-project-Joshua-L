package com.example.pokemon_search_backend.Controller;


import com.example.pokemon_search_backend.DTO.NewUserDTO;
import com.example.pokemon_search_backend.Service.NewUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    private final NewUserService userService;

    @Autowired
    public UserController(NewUserService userService) {
        this.userService = userService;
    }

    @RequestMapping
    public ResponseEntity<List<NewUserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewUserDTO> getUserById(@PathVariable int id) {
        return userService.getUsereById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/register")
    public ResponseEntity<NewUserDTO> registerUser(@RequestBody NewUserDTO newUserDTO) {
        try {
            NewUserDTO createdUser = userService.createUser(newUserDTO);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<NewUserDTO> updateUser(@PathVariable int id, @RequestBody NewUserDTO userDTO) {
        return userService.updateUser(id, userDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
