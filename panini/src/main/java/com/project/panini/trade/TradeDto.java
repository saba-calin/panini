package com.project.panini.trade;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TradeDto {

    private Long id;

    private Status status;

    private String proposerUsername;

    private String receiverUsername;
}
