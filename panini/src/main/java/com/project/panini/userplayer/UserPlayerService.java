package com.project.panini.userplayer;

import com.project.panini.player.Player;
import com.project.panini.player.PlayerRepository;
import com.project.panini.player.dto.PlayerDto;
import com.project.panini.user.User;
import com.project.panini.util.UserContextService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class UserPlayerService {

    private final UserPlayerRepository userPlayerRepository;
    private final PlayerRepository playerRepository;

    private final UserContextService userContextService;

    private final Random random = new Random();

    public List<PlayerDto> generatePlayers() {
        List<PlayerDto> list = new ArrayList<>();
        User user = this.userContextService.getPrincipal();

        for (int i = 1; i <= 7; i++) {
            // generate the player id
            long playerId = 1 + this.random.nextInt(480);
            Player player = this.playerRepository.findById(playerId).orElseThrow(() -> new IllegalStateException("Player not found"));

            // save the user_player in the database
            UserPlayer userPlayer = UserPlayer.builder()
                    .user(user)
                    .player(player)
                    .build();
            this.userPlayerRepository.save(userPlayer);

            // add the playerDto to the list
            PlayerDto playerDto = PlayerDto.builder()
                    .id(player.getId())
                    .name(player.getName())
                    .shirtNumber(player.getShirtNumber())
                    .photo(player.getPhoto())
                    .build();
            list.add(playerDto);
        }

        return list;
    }

    @Transactional
    public List<PlayerDto> getDoubles() {
        long userId = this.userContextService.getUserId();
        return this.userPlayerRepository.getDoubles(userId);
    }

    public boolean isAllowedToClaimPrize() {
        long userId = this.userContextService.getUserId();
        return this.userPlayerRepository.getNumberOfUnlockedPlayers(userId) == this.playerRepository.count();
    }
}
