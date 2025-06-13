package com.project.panini.player;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlayerDto {

    private Long id;

    private String name;

    private int shirtNumber;

    private byte[] photo;
}
