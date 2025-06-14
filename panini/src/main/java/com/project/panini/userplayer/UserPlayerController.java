package com.project.panini.userplayer;

import com.project.panini.player.dto.PlayerDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/doubles/by-username")
    public ResponseEntity<List<PlayerDto>> getDoublesByUsername(@RequestParam("username") String username) {
        return ResponseEntity.status(HttpStatus.OK).body(this.userPlayerService.getDoublesByUsername(username));
    }

    @GetMapping("/claim-prize")
    public ResponseEntity<Boolean> isAllowedToClaimPrize() {
        return ResponseEntity.status(HttpStatus.OK).body(this.userPlayerService.isAllowedToClaimPrize());
    }
}
