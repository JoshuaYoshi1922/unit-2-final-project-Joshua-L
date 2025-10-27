package com.example.pokemon_search_backend.Repository;

import com.example.pokemon_search_backend.Model.NewUserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<NewUserModel, Integer> {
    Optional<NewUserModel> findByUsername(String username);
    boolean existsByUsername(String username);
}

