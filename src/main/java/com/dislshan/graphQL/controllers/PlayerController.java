package com.dislshan.graphQL.controllers;

import com.dislshan.graphQL.models.Player;
import com.dislshan.graphQL.models.Team;
import com.dislshan.graphQL.services.PlayerService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @QueryMapping
    public List<Player> findAll() {
        return this.playerService.findAllPlayers();
    }

    @QueryMapping
    public Optional<Player> findOne(@Argument Integer id) {
        return this.playerService.findPlayer(id);
    }

    @MutationMapping
    public Player create(@Argument String name, @Argument Team team) {
        return this.playerService.createPlayer(name, team);

    }

    @MutationMapping
    public Player delete(@Argument Integer id) {
        return this.playerService.deletePlayer(id);
    }

    @MutationMapping
    public Player update(@Argument Integer id, @Argument String name, @Argument Team team) {
        return this.playerService.updatePlayer(id, name, team);
    }
}
