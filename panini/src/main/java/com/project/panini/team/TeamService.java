package com.project.panini.team;

import com.project.panini.player.Player;
import com.project.panini.player.PlayerRepository;
import com.project.panini.player.dto.TeamPlayerDto;
import com.project.panini.userplayer.UserPlayerRepository;
import com.project.panini.util.UserContextService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;
    private final UserPlayerRepository userPlayerRepository;

    private final UserContextService userContextService;

    @Transactional
    public List<TeamPlayerDto> getTeamById(long teamId) {
        List<TeamPlayerDto> list = new ArrayList<>();
        long userId = this.userContextService.getUserId();

        List<Player> players = this.playerRepository.findPlayersByTeamId(teamId);
        List<Long> unlockedPlayerIds = this.userPlayerRepository.getUnlockedPlayerIdsForTeam(userId, teamId);

        for (Player player : players) {
            boolean isUnlocked = unlockedPlayerIds.contains(player.getId());
            TeamPlayerDto teamPlayerDto = TeamPlayerDto.builder()
                    .id(player.getId())
                    .name(player.getName())
                    .shirtNumber(player.getShirtNumber())
                    .isTitular(player.isTitular())
                    .isUnlocked(isUnlocked)
                    .photo(player.getPhoto())
                    .build();
            list.add(teamPlayerDto);
        }

        return list;
    }
}
