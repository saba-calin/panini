package com.project.panini.trade;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TradeRequest {

    @NotEmpty(message = "You must select at least 1 card which you want to give")
    private List<Long> proposerTradeIds;

    @NotEmpty(message = "You must select at least 1 card which you want to get")
    private List<Long> receiverTradeIds;

    @NotBlank(message = "You must select a trade partner")
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
