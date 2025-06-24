package com.project.panini.trade;

import com.project.panini.player.Player;
import com.project.panini.player.PlayerRepository;
import com.project.panini.player.dto.PlayerDto;
import com.project.panini.tradeplayer.TradePlayer;
import com.project.panini.tradeplayer.TradePlayerRepository;
import com.project.panini.user.User;
import com.project.panini.user.UserRepository;
import com.project.panini.userplayer.UserPlayer;
import com.project.panini.userplayer.UserPlayerRepository;
import com.project.panini.util.UserContextService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TradeService {

    private final TradeRepository tradeRepository;
    private final PlayerRepository playerRepository;
    private final UserRepository userRepository;

    private final TradePlayerRepository tradePlayerRepository;
    private final UserPlayerRepository userPlayerRepository;

    private final UserContextService userContextService;

    @Transactional
    public void makeTrade(TradeRequest request) {
        long proposerId = this.userContextService.getUserId();
        long receiverId = this.userRepository.findByUsername(request.getReceiver())
                .orElseThrow(() -> new RuntimeException("User not found"))
                .getId();

        User proposer = this.userRepository.getReferenceById(proposerId);
        User receiver = this.userRepository.getReferenceById(receiverId);

        Trade trade = Trade.builder()
                .proposer(proposer)
                .receiver(receiver)
                .status(Status.PENDING)
                .build();
        trade = this.tradeRepository.save(trade);

        for (Long playerId : request.getProposerTradeIds()) {
            Player player = this.playerRepository.getReferenceById(playerId);

            // delete the player from the user's inventory
            UserPlayer userPlayer = this.userPlayerRepository.findFirstByUserIdAndPlayerId(proposerId, playerId)
                    .orElseThrow(() -> new RuntimeException("UserPlayer not found"));
            this.userPlayerRepository.delete(userPlayer);

            // add the player to the trade
            TradePlayer tradePlayer = TradePlayer.builder()
                    .trade(trade)
                    .player(player)
                    .proposerSide(true)
                    .build();
            this.tradePlayerRepository.save(tradePlayer);
        }

        for (Long playerId : request.getReceiverTradeIds()) {
            Player player = this.playerRepository.getReferenceById(playerId);

            // delete the player from the user's inventory
            UserPlayer userPlayer = this.userPlayerRepository.findFirstByUserIdAndPlayerId(receiverId, playerId)
                    .orElseThrow(() -> new RuntimeException("UserPlayer not found"));
            this.userPlayerRepository.delete(userPlayer);

            // add the player to the trade
            TradePlayer tradePlayer = TradePlayer.builder()
                    .trade(trade)
                    .player(player)
                    .proposerSide(false)
                    .build();
            this.tradePlayerRepository.save(tradePlayer);
        }
    }

    public List<TradeDto> getTrades() {
        long userId = this.userContextService.getUserId();
        return this.tradeRepository.getTrades(userId);
    }

    @Transactional
    public List<PlayerDto> getProposerPlayers(long tradeId) {
        return this.tradeRepository.getProposerPlayers(tradeId);
    }

    @Transactional
    public List<PlayerDto> getReceiverPlayers(long tradeId) {
        return this.tradeRepository.getReceiverPlayers(tradeId);
    }
}
