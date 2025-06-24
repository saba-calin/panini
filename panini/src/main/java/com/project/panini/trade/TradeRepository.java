package com.project.panini.trade;

import com.project.panini.player.dto.PlayerDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TradeRepository extends JpaRepository<Trade, Long> {

    @Query("""
        SELECT new com.project.panini.trade.TradeDto(
            t.id,
            t.status,
            pu.username,
            ru.username
        )
        FROM trades t
        INNER JOIN users pu ON t.proposer.id = pu.id
        INNER JOIN users ru ON t.receiver.id = ru.id
        WHERE t.proposer.id = :userId OR t.receiver.id = :userId
    """)
    List<TradeDto> getTrades(@Param("userId") Long userId);

    @Query("""
        SELECT new com.project.panini.player.dto.PlayerDto(
            p.id,
            p.name,
            p.shirtNumber,
            p.photo
        )
        FROM trades t
        INNER JOIN trade_player tp ON t.id = tp.trade.id
        INNER JOIN players p ON tp.player.id = p.id
        WHERE t.id = :tradeId AND tp.proposerSide = true
    """)
    List<PlayerDto> getProposerPlayers(@Param("tradeId") Long tradeId);

    @Query("""
        SELECT new com.project.panini.player.dto.PlayerDto(
            p.id,
            p.name,
            p.shirtNumber,
            p.photo
        )
        FROM trades t
        INNER JOIN trade_player tp ON t.id = tp.trade.id
        INNER JOIN players p ON tp.player.id = p.id
        WHERE t.id = :tradeId AND tp.proposerSide = false
    """)
    List<PlayerDto> getReceiverPlayers(@Param("tradeId") Long tradeId);
}
