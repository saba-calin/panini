package com.project.panini.player.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamPlayerDto {

    private Long id;

    private String name;

    private int shirtNumber;

    private boolean isTitular;

    private boolean isUnlocked;

    private byte[] photo;
}
