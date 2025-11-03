package com.example.pokemon_search_backend.Service;



import com.example.pokemon_search_backend.DTO.UserDTO;

import com.example.pokemon_search_backend.Model.UserModel;
import com.example.pokemon_search_backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

    }

    public Optional<UserDTO> getUserById(int id){
        return userRepository.findById(id)
                .map(this::convertToDTO);
    }

    public Optional<UserDTO> getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(this::convertToDTO);
    }

    public UserDTO createUser(UserDTO userDTO) {
        if(userRepository.existsByUsername(userDTO.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        UserModel userModel = new UserModel(
                userDTO.getUsername(),
                userDTO.getPassword(),
                userDTO.getEmail(),
                userDTO.getFavoritePokemons()
        );

        UserModel savedUser = userRepository.save(userModel);
        return convertToDTO(savedUser);
    }

    public Optional<UserDTO> updateUser(int id, UserDTO userDTO) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setUsername(userDTO.getUsername());
                    existingUser.setEmail(userDTO.getEmail());
                    existingUser.setFavoritePokemons(userDTO.getFavoritePokemons());
                    return convertToDTO(userRepository.save(existingUser));
                });
    }
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
    private UserDTO convertToDTO(UserModel userModel) {
        return new UserDTO(
                userModel.getId(),
                userModel.getUsername(),
//                userModel.getPassword(),
                userModel.getEmail(),
                userModel.getFavoritePokemons()
        );
    }
}
