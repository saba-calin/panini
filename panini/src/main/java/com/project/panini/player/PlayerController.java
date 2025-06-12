package com.project.panini.player;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/player")
@AllArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping
    public List<Player> getPlayersByTeamId(@RequestParam("team_id") long id) {
        return this.playerService.getPlayersByTeamId(id);
    }
}
