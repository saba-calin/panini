package com.project.panini.trade;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/trade")
@AllArgsConstructor
public class TradeController {

    private final TradeService tradeService;

    @PostMapping("/make-trade")
    public ResponseEntity<Void> makeTrade(@RequestBody TradeRequest request) {
        this.tradeService.makeTrade(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
