package com.example.pokemon_search_backend.Service;


//import com.example.pokemon_search_backend.DTO.TeamDTO;
import com.example.pokemon_search_backend.Model.TeamModel;
import com.example.pokemon_search_backend.Model.UserModel;
import com.example.pokemon_search_backend.Repository.TeamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service
//public class TeamService {
//    private final UserService userService;
//    private final TeamRepo teamRepo;
//
//    @Autowired
//    public TeamService(UserService userService, TeamRepo teamRepo) {
//        this.userService = userService;
//        this.teamRepo = teamRepo;
//    }
//
////    public TeamDTO createTeam(TeamDTO teamDTO) {
////        UserModel user = userService.getUserById(teamDTO.getUserId())
////                .orElseThrow(() -> new RuntimeException("User not found with ID: " + teamDTO.getUserId()));
////        TeamModel team = user.getTeam();
////        if (team != null) {
////            throw new RuntimeException("User already has a team");
////            team = new TeamModel();
////            team.setUser(user);
//        }
////        return
////    }
//
//    private TeamModel mapToEntity(TeamDTO teamDTO, UserModel user) {
//        TeamModel team = new TeamModel();
//        team.setId(teamDTO.getId());
//        team.setTeamName(teamDTO.getTeamName());
//        team.setUser(user);
//        return team;
//    }



