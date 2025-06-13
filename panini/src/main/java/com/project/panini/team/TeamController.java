package com.project.panini.team;

import com.project.panini.player.dto.TeamPlayerDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/team")
@AllArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @GetMapping
    public ResponseEntity<List<TeamPlayerDto>> getTeamById(@RequestParam("team_id") long teamId) {
        return ResponseEntity.status(HttpStatus.OK).body(this.teamService.getTeamById(teamId));
    }
}
