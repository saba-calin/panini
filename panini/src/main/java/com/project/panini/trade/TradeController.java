package com.project.panini.trade;

import com.project.panini.player.dto.PlayerDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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

    @PostMapping("/make-trade")
    public ResponseEntity<Void> makeTrade(@Valid @RequestBody TradeRequest request) {
        this.tradeService.makeTrade(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
