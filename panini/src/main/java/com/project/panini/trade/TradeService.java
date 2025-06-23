package com.project.panini.trade;

import com.project.panini.user.User;
import com.project.panini.user.UserRepository;
import com.project.panini.util.UserContextService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TradeService {

    private final TradeRepository tradeRepository;
    private final UserRepository userRepository;
    private final UserContextService userContextService;

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
    }
}
