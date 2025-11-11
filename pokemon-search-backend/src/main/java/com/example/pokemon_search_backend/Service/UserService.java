package com.example.pokemon_search_backend.Service;

import com.example.pokemon_search_backend.DTO.UserDTO;
import com.example.pokemon_search_backend.Model.UserModel;
import com.example.pokemon_search_backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public List<UserModel> getAllUsers() {
        return userRepository.findAll();


    }

    public Optional<UserModel> getUserById(int id) {
        return userRepository.getUserById(id);
    }

    public Optional<UserModel> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    public UserModel createUser(UserDTO userDTO) {
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        UserModel userModel = new UserModel();
        userModel.setUsername(userDTO.getUsername());
        userModel.setEmail(userDTO.getEmail());
        // Hash the password before saving
        userModel.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userModel.setTeamName(userDTO.getTeamName());


        UserModel savedUser = userRepository.save(userModel);
        return savedUser;
    }

    public Optional<UserModel> authenticateUser(String username, String password) {
        Optional<UserModel> userOptional = userRepository.findByUsername(username);
        
        if (userOptional.isPresent()) {
            UserModel user = userOptional.get();
            // Check if the provided password matches the hashed password
            if (passwordEncoder.matches(password, user.getPassword())) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public UserModel updateUser(int id, UserDTO userDTO) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    // Only update fields that are provided (non-null and not blank)
                    if (userDTO.getUsername() != null && !userDTO.getUsername().isBlank()) {
                        existingUser.setUsername(userDTO.getUsername());
                    }

                    if (userDTO.getEmail() != null && !userDTO.getEmail().isBlank()) {
                        existingUser.setEmail(userDTO.getEmail());
                    }

                    if (userDTO.getTeamName() != null && !userDTO.getTeamName().isBlank()) {
                        existingUser.setTeamName(userDTO.getTeamName());
                    }

                    // If password provided and not blank, hash and update
                    if (userDTO.getPassword() != null && !userDTO.getPassword().isBlank()) {
                        existingUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
                    }
                    return userRepository.save(existingUser);
                })
                .orElseThrow(() -> new RuntimeException("User with ID " + id + " not found."));
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }


}
