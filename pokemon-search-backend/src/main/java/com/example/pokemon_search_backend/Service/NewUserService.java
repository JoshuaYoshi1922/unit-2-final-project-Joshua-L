package com.example.pokemon_search_backend.Service;


import com.example.pokemon_search_backend.DTO.NewUserDTO;
import com.example.pokemon_search_backend.Model.NewUserModel;
import com.example.pokemon_search_backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NewUserService {

    private final UserRepository userRepository;

    @Autowired
    public NewUserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public List<NewUserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

    }

    public Optional<NewUserDTO> getUserById(int id){
        return userRepository.findById(id)
                .map(this::convertToDTO);
    }

    public Optional<NewUserDTO> getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(this::convertToDTO);
    }

    public NewUserDTO createUser(NewUserDTO newUserDTO) {
        if(userRepository.existsByUsername(newUserDTO.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        NewUserModel newUserModel = new NewUserModel(
        newUserDTO.getUsername(),
        newUserDTO.getPassword(),
        newUserDTO.getEmail()
        );

        NewUserModel savedUser = userRepository.save(newUserModel);
        return convertToDTO(savedUser);
    }

    public Optional<NewUserDTO> updateUser(int id, NewUserDTO newUserDTO) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setUsername(newUserDTO.getUsername());
                    existingUser.setPassword(newUserDTO.getPassword());
                    existingUser.setEmail(newUserDTO.getEmail());
                    return convertToDTO(userRepository.save(existingUser));
                });
    }
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
    private NewUserDTO convertToDTO(NewUserModel userModel) {
        return new NewUserDTO(
                userModel.getId(),
                userModel.getUsername(),
                userModel.getPassword(),
                userModel.getEmail()
        );
    }
}
