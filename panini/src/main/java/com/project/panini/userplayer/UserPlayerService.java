package com.project.panini.userplayer;

import com.github.javafaker.Faker;
import com.project.panini.player.Player;
import com.project.panini.player.PlayerDto;
import com.project.panini.player.PlayerRepository;
import com.project.panini.user.User;
import com.project.panini.util.UserContextService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserPlayerService {

    private final UserPlayerRepository userPlayerRepository;
    private final PlayerRepository playerRepository;

    private final UserContextService userContextService;

    private final Faker faker = new Faker();

    public List<PlayerDto> generatePlayers() {
        List<PlayerDto> list = new ArrayList<>();
        User user = this.userContextService.getPrincipal();

        for (int i = 1; i <= 7; i++) {
            // generate the player id
            long playerId = this.faker.number().numberBetween(1, 481);
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
}
