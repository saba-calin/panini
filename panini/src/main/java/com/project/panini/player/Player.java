package com.project.panini.player;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.panini.team.Team;
import com.project.panini.tradeplayer.TradePlayer;
import com.project.panini.userplayer.UserPlayer;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "shirt_number")
    private int shirtNumber;

    private float height;

    private float weight;

    private String position;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "is_titular")
    private boolean isTitular;

    @Lob
    private byte[] photo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    @JsonIgnore
    private Team team;

    @OneToMany(mappedBy = "player")
    @JsonIgnore
    private List<UserPlayer> userPlayers;

    @OneToMany(mappedBy = "player")
    @JsonIgnore
    private List<TradePlayer> tradePlayers;
}
