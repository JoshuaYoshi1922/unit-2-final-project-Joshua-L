package com.example.pokemon_search_backend.Repository;

import com.example.pokemon_search_backend.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRespository  extends JpaRepository<UserModel, Integer> {
}
