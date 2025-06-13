package com.project.panini.player;

import lombok.*;

import java.util.Arrays;

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

    @Override
    public String toString() {
        return "PlayerDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", shirtNumber=" + shirtNumber +
                ", photo=" + Arrays.toString(photo) +
                '}';
    }
}
