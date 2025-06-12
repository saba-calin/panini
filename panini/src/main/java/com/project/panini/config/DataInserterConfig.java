package com.project.panini.config;

import com.github.javafaker.Faker;
import com.project.panini.coach.Coach;
import com.project.panini.coach.CoachRepository;
import com.project.panini.player.Player;
import com.project.panini.player.PlayerRepository;
import com.project.panini.team.Team;
import com.project.panini.team.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;

@Component
@AllArgsConstructor
public class DataInserterConfig implements CommandLineRunner {

    private final TeamRepository teamRepository;
    private final CoachRepository coachRepository;
    private final PlayerRepository playerRepository;

    private final Faker faker = new Faker();

    @Override
    public void run(String... args) throws Exception {
        insertRosters();
    }

    private void insertRosters() {
        this.teamRepository.findById((long) 1).orElseThrow();
        long playerCount = this.playerRepository.count();
        if (playerCount != 480) {
            insertTeams();
            insertCoaches();
            insertPlayers();
        }
    }

    private void insertTeams() {
        for (int i = 1; i <= 24; i++) {
            this.teamRepository.save(new Team());
        }
    }

    private void insertCoaches() {
        byte[] photo = loadPhoto("./photos/coach_photo");

        for (int i = 1; i <= 24; i++) {
            String name = this.faker.name().fullName();
            Team team = this.teamRepository.findById((long) i).orElseThrow();

            Coach coach = Coach.builder()
                    .name(name)
                    .photo(photo)
                    .team(team)
                    .build();

            this.coachRepository.save(coach);
        }
    }

    private void insertPlayers() {
        byte[] photo = loadPhoto("./photos/player_photo");

        for (int i = 1; i <= 24; i++) {
            Team team = this.teamRepository.findById((long) i).orElseThrow();

            for (int j = 1; j <= 20; j++) {
                String name = this.faker.name().fullName();
                int shirtNumber = this.faker.number().numberBetween(0, 100);
                float height = this.faker.number().numberBetween(150, 200);
                float weight = this.faker.number().numberBetween(50, 100);
                String position = "ST";
                String description = this.faker.lorem().sentence(this.faker.number().numberBetween(10, 20));
                boolean isTitular = j <= 11;

                Player player = Player.builder()
                        .name(name)
                        .shirtNumber(shirtNumber)
                        .height(height)
                        .weight(weight)
                        .position(position)
                        .description(description)
                        .isTitular(isTitular)
                        .photo(photo)
                        .team(team)
                        .build();

                this.playerRepository.save(player);
            }
        }
    }

    private byte[] loadPhoto(String path) {
        try {
            InputStream inputStream = getClass().getResourceAsStream(path);
            return StreamUtils.copyToByteArray(inputStream);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new byte[0];
        }
    }
}
