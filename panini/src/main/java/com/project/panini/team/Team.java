package com.project.panini.team;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.panini.coach.Coach;
import com.project.panini.player.Player;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne(mappedBy = "team", fetch = FetchType.LAZY)
    @JsonIgnore
    private Coach coach;

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Player> players;
}
