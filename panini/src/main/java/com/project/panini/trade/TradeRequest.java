package com.project.panini.trade;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TradeRequest {

    private List<Integer> proposerTradeIds;

    private List<Integer> receiverTradeIds;

    private String receiver;

    @Override
    public String toString() {
        return "TradeRequest{" +
                "proposerTradeIds=" + proposerTradeIds +
                ", receiverTradeIds=" + receiverTradeIds +
                ", receiver='" + receiver + '\'' +
                '}';
    }
}
