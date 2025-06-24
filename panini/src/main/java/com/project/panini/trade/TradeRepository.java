package com.project.panini.trade;

import com.project.panini.player.dto.PlayerDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;
import java.util.Optional;

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
        ORDER BY t.id ASC
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
        WHERE t.id = :tradeId
    """)
    Optional<TradeDto> getTradeById(@Param("tradeId") Long tradeId);

    @Modifying
    @Query("""
        UPDATE trades t
        SET t.status = :status
        WHERE t.id = :tradeId
    """)
    void updateTradeStatus(@Param("status") Status status, @Param("tradeId") Long tradeId);

    @Query("""
        SELECT tp.player.id
        FROM trades t
        INNER JOIN trade_player tp ON t.id = tp.trade.id
        WHERE t.id = :tradeId AND tp.proposerSide = true
    """)
    List<Long> getProposerTradeIds(@Param("tradeId") Long tradeId);

    @Query("""
        SELECT tp.player.id
        FROM trades t
        INNER JOIN trade_player tp ON t.id = tp.trade.id
        WHERE t.id = :tradeId AND tp.proposerSide = false
    """)
    List<Long> getReceiverTradeIds(@Param("tradeId") Long tradeId);
}
