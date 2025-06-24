package com.project.panini.trade;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.panini.tradeplayer.TradePlayer;
import com.project.panini.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "trades")
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "proposer_id")
    private User proposer;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiver;

    @OneToMany(mappedBy = "trade")
    @JsonIgnore
    private List<TradePlayer> tradePlayers;
}
