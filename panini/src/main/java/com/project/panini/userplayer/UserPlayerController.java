package com.project.panini.userplayer;

import com.project.panini.player.dto.PlayerDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user-player")
@AllArgsConstructor
public class UserPlayerController {

    private final UserPlayerService userPlayerService;

    @PostMapping("/buy-pack")
    public ResponseEntity<List<PlayerDto>> generatePlayers() {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userPlayerService.generatePlayers());
    }

    @GetMapping("/doubles")
    public ResponseEntity<List<PlayerDto>> getDoubles() {
        return ResponseEntity.status(HttpStatus.OK).body(this.userPlayerService.getDoubles());
    }
}
