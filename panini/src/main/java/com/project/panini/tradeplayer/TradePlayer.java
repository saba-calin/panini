package com.project.panini.tradeplayer;

import com.project.panini.player.Player;
import com.project.panini.trade.Trade;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "trade_player")
public class TradePlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "proposer_side")
    private boolean proposerSide;

    @ManyToOne
    @JoinColumn(name = "trade_id")
    private Trade trade;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;
}
