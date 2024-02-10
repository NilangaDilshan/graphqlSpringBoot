package com.dislshan.graphQL.services;

import com.dislshan.graphQL.models.Player;
import com.dislshan.graphQL.models.Team;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PlayerService {

    private List<Player> players = new ArrayList<>();
    AtomicInteger id = new AtomicInteger(0);

    public List<Player> findAllPlayers() {
        return this.players;
    }

    public Optional<Player> findPlayer(Integer id) {
        return this.players.stream().filter(player -> Objects.equals(player.id(), id)).findFirst();
    }

    public Player createPlayer(String name, Team team) {
        Player newPlayer = new Player(id.incrementAndGet(), name, team);
        this.players.add(newPlayer);
        return newPlayer;
    }

    public Player deletePlayer(Integer id) {
        Player playerToBeDeleted = this.players.stream()
                .filter(player -> player.id().equals(id)).findFirst()
                .orElseThrow(IllegalArgumentException::new);
        this.players.remove(playerToBeDeleted);
        return playerToBeDeleted;
    }

    public Player updatePlayer(Integer id, String name, Team team) {
        Player updatedPlayer = new Player(id, name, team);
        Optional<Player> oldPlayer = this.players.stream()
                .filter(p -> p.id().equals(id)).findFirst();
        oldPlayer.ifPresentOrElse(player -> {
                    int index = this.players.indexOf(player);
                    this.players.set(index, updatedPlayer);
                },
                () -> {
                    throw new IllegalArgumentException("No Player Existing");
                });
        return updatedPlayer;
    }

    @PostConstruct
    public void addData() {
        players.add(new Player(id.incrementAndGet(), "MS Dhoni", Team.CSK));
        players.add(new Player(id.incrementAndGet(), "Sanath Jayasuriya", Team.MI));
        players.add(new Player(id.incrementAndGet(), "Kumar Sangakkara", Team.PKXI));
        players.add(new Player(id.incrementAndGet(), "TM Dilshan", Team.DC));
    }
}
