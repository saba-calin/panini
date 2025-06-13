package com.project.panini.player;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/player")
@AllArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping
    public ResponseEntity<List<Player>> getPlayersByTeamId(@RequestParam("team_id") long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.playerService.getPlayersByTeamId(id));
    }
}
