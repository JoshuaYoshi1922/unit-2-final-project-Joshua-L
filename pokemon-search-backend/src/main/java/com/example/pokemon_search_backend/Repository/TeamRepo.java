package com.example.pokemon_search_backend.Repository;

import com.example.pokemon_search_backend.Model.TeamModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepo extends JpaRepository<TeamModel, Integer> {

}
