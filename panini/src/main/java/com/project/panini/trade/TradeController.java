package com.project.panini.trade;

import com.project.panini.player.dto.PlayerDto;
import com.project.panini.trade.request.AcceptRequest;
import com.project.panini.trade.request.DeclineRequest;
import com.project.panini.trade.request.TradeRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/trade")
@AllArgsConstructor
public class TradeController {

    private final TradeService tradeService;

    @GetMapping
    public ResponseEntity<List<TradeDto>> getTrades() {
        return ResponseEntity.status(HttpStatus.OK).body(this.tradeService.getTrades());
    }

    @GetMapping("/proposer-players")
    public ResponseEntity<List<PlayerDto>> getProposerPlayers(@RequestParam("trade-id") long tradeId) {
        return ResponseEntity.status(HttpStatus.OK).body(this.tradeService.getProposerPlayers(tradeId));
    }

    @GetMapping("/receiver-players")
    public ResponseEntity<List<PlayerDto>> getReceiverPlayers(@RequestParam("trade-id") long tradeId) {
        return ResponseEntity.status(HttpStatus.OK).body(this.tradeService.getReceiverPlayers(tradeId));
    }

    @GetMapping("/by-id")
    public ResponseEntity<TradeDto> getReceiverUsername(@RequestParam("trade-id") long tradeId) {
        return ResponseEntity.status(HttpStatus.OK).body(this.tradeService.getTradeById(tradeId));
    }

    @PostMapping("/make-trade")
    public ResponseEntity<Void> makeTrade(@Valid @RequestBody TradeRequest request) {
        this.tradeService.makeTrade(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/accept-trade")
    public ResponseEntity<Void> acceptTrade(@RequestBody AcceptRequest request) {
        this.tradeService.acceptTrade(request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/decline-trade")
    public ResponseEntity<Void> declineTrade(@RequestBody DeclineRequest request) {
        this.tradeService.declineTrade(request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
