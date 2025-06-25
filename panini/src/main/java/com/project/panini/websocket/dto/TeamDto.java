package com.project.panini.websocket.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamDto {

    private String name;

    private int homeScore;

    private int awayScore;
}
