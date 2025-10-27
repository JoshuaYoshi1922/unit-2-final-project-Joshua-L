package com.example.pokemon_search_backend.Service;


import com.example.pokemon_search_backend.DTO.UserFavPokemonDTO;
import com.example.pokemon_search_backend.Model.UserFavPokemon;
import com.example.pokemon_search_backend.Model.NewUserModel;
import com.example.pokemon_search_backend.Repository.UserFavPokemonRepository;
import com.example.pokemon_search_backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserFavPokemonService {

    private final UserFavPokemonRepository favPokemonRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserFavPokemonService(UserFavPokemonRepository favPokemonRepository, UserRepository userRepository) {
        this.favPokemonRepository = favPokemonRepository;
        this.userRepository = userRepository;
    }

    public List<UserFavPokemonDTO> getFavoritesByUserId(int userId) {
        List<UserFavPokemon> favorites = favPokemonRepository.findByUserId(userId);

        return favorites.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public UserFavPokemonDTO addFavorite(UserFavPokemonDTO favPokemonDTO) {
        Optional<UserFavPokemon> existingFavorite = favPokemonRepository.findByUserIdAndPokemonId(favPokemonDTO.getUserId(), favPokemonDTO.getPokemonId());

        if(existingFavorite.isPresent()) {
            return convertToDTO(existingFavorite.get());
        }

        NewUserModel user = userRepository.findById(favPokemonDTO.getUserId())
                .orElseThrow(()-> new RuntimeException("User not found with ID: " + favPokemonDTO.getUserId()));

        UserFavPokemon newFavorite = new UserFavPokemon();
        newFavorite.setPokemonId(favPokemonDTO.getPokemonId());
        newFavorite.setUser(user);

        UserFavPokemon saved = favPokemonRepository.save(newFavorite);
        return convertToDTO(saved);
    }

    public void removeFavorite(int userId, int pokemonId) {
        Optional<UserFavPokemon> favorite = favPokemonRepository.findByUserIdAndPokemonId(userId, pokemonId);

        favorite.ifPresent(favPokemonRepository::delete);
    }

    private UserFavPokemonDTO convertToDTO(UserFavPokemon entity) {
        return new UserFavPokemonDTO(entity.getId(), entity.getUser().getId(), entity.getPokemonId());
    }


}
