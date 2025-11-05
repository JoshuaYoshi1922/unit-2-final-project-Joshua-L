package com.example.pokemon_search_backend.Service;

import com.example.pokemon_search_backend.DTO.UserDTO;
import com.example.pokemon_search_backend.Model.UserModel;
import com.example.pokemon_search_backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserModel> getAllUsers() {
        return userRepository.findAll();


    }

    public Optional<UserModel> getUserById(int id) {
        return userRepository.findById(id);
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
        userModel.setPassword(userDTO.getPassword());
        userModel.setTeamName(userDTO.getTeamName());


        UserModel savedUser = userRepository.save(userModel);
        return savedUser;
    }

    public UserModel updateUser(int id, UserDTO userDTO) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setUsername(userDTO.getUsername());
                    existingUser.setEmail(userDTO.getEmail());
                    existingUser.setPassword(userDTO.getPassword());
                    existingUser.setTeamName(userDTO.getTeamName());
                    return userRepository.save(existingUser);
                })
                .orElseThrow(() -> new RuntimeException("User with ID " + id + " not found."));
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }


}
