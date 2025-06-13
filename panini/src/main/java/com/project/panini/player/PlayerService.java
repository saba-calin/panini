package com.project.panini.player;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;

    @Transactional
    public List<Player> getPlayersByTeamId(long id) {
        return this.playerRepository.findPlayersByTeamId(id);
    }

    public Player getPlayerById(long id) {
        return this.playerRepository.findById(id).orElseThrow(() -> new IllegalStateException("Player not found"));
    }
}
