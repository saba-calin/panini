package com.project.panini.websocket;

import com.project.panini.team.TeamRepository;
import com.project.panini.util.Pair;
import com.project.panini.websocket.dto.TeamDto;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Component
@AllArgsConstructor
public class Broadcaster {

    private final SimpMessagingTemplate messagingTemplate;
    private final TeamRepository teamRepository;

    private final Random random = new Random();

    private List<TeamDto> roundOf16;
    private List<TeamDto> quarterFinal;
    private List<TeamDto> semiFinal;
    private List<TeamDto> finalMatch;
    private List<TeamDto> winner;

    @Scheduled(fixedRate = 5000)
    public void broadcast() {
        if (this.roundOf16.isEmpty()) {
            this.roundOf16 = get16RandomTeams();
        } else if (this.quarterFinal.size() < 8) {
            TeamDto team = getTeamForQuarterFinal();
            this.quarterFinal.add(team);
        } else if (this.semiFinal.size() < 4) {
            TeamDto team = getTeamForSemiFinal();
            this.semiFinal.add(team);
        } else if (this.finalMatch.size() < 2) {
            TeamDto team = getTeamForFinal();
            this.finalMatch.add(team);
        } else if (this.winner.isEmpty()) {
            TeamDto winner = getWinner();
            this.winner.add(winner);
        } else {
            startNewChampionship();
        }

        messagingTemplate.convertAndSend("/topic/greeting", getChampionshipList());
    }

    private void startNewChampionship() {
        this.roundOf16.clear();
        this.quarterFinal.clear();
        this.semiFinal.clear();
        this.finalMatch.clear();
        this.winner.clear();
    }

    private TeamDto getWinner() {
        String homeTeamName = this.finalMatch.get(0).getName();
        String awayTeamName = this.finalMatch.get(1).getName();

        return getWinningTeam(homeTeamName, awayTeamName);
    }

    private TeamDto getTeamForFinal() {
        int idx = this.finalMatch.size() * 2 + 1;
        String homeTeamName = this.semiFinal.get(idx).getName();
        String awayTeamName = this.semiFinal.get(idx - 1).getName();

        return getWinningTeam(homeTeamName, awayTeamName);
    }

    private TeamDto getTeamForSemiFinal() {
        int idx = this.semiFinal.size() * 2 + 1;
        String homeTeamName = this.quarterFinal.get(idx).getName();
        String awayTeamName = this.quarterFinal.get(idx - 1).getName();

        return getWinningTeam(homeTeamName, awayTeamName);
    }

    private TeamDto getTeamForQuarterFinal() {
        int idx = this.quarterFinal.size() * 2 + 1;
        String homeTeamName = this.roundOf16.get(idx).getName();
        String awayTeamName = this.roundOf16.get(idx - 1).getName();

        return getWinningTeam(homeTeamName, awayTeamName);
    }

    private TeamDto getWinningTeam(String homeTeamName, String awayTeamName) {
        Pair<Integer, Integer> score = getRandomScore();
        int homeScore = score.getFirst();
        int awayScore = score.getSecond();

        if (homeScore > awayScore) {
            return new TeamDto(homeTeamName, homeScore, awayScore);
        } else {
            return new TeamDto(awayTeamName, awayScore, homeScore);
        }
    }

    private Pair<Integer, Integer> getRandomScore() {
        int homeScore = this.random.nextInt(5);
        int awayScore = this.random.nextInt(5);

        while (homeScore == awayScore) {
            homeScore = this.random.nextInt(5);
            awayScore = this.random.nextInt(5);
        }

        return new Pair<>(homeScore, awayScore);
    }

    private List<TeamDto> get16RandomTeams() {
        List<TeamDto> teams = new ArrayList<>();

        List<String> teamNames = this.teamRepository.getAllTeamNames();
        Collections.shuffle(teamNames, this.random);
        for (int i = 0; i < 16; i++) {
            TeamDto teamDto = TeamDto.builder()
                    .name(teamNames.get(i))
                    .build();
            teams.add(teamDto);
        }

        return teams;
    }

    private List<List<TeamDto>> getChampionshipList() {
        List<List<TeamDto>> list = new ArrayList<>();

        list.add(this.roundOf16);
        list.add(this.quarterFinal);
        list.add(this.semiFinal);
        list.add(this.finalMatch);
        list.add(this.winner);

        return list;
    }
}
