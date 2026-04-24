package com.jpmc.midascore;

import com.jpmc.midascore.entity.TransactionRecord;
import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.foundation.Transaction;
import com.jpmc.midascore.repository.TransactionRepository;
import com.jpmc.midascore.repository.UserRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import com.jpmc.midascore.service.IncentiveService;

@Component
public class KafkaConsumer {
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final IncentiveService incentiveService; // Add this line

    public KafkaConsumer(TransactionRepository transactionRepository, 
                         UserRepository userRepository, 
                         IncentiveService incentiveService) { // Add to constructor
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.incentiveService = incentiveService;
    }

    @KafkaListener(topics = "transactions")
    public void listen(Transaction transaction) {
        UserRecord sender = userRepository.findById(transaction.getSenderId());
        UserRecord receiver = userRepository.findById(transaction.getRecipientId());

        if (sender != null && receiver != null && sender.getBalance() >= transaction.getAmount()) {
            
            // TASK 5 LOGIC: Calculate bonus
            float incentive = incentiveService.calculateIncentive(transaction);
            
            // Add bonus to the RECEIVER (or sender, depending on bank rules)
            // Usually, incentives go to the sender as cashback
            sender.setBalance(sender.getBalance() - transaction.getAmount() + incentive);
            receiver.setBalance(receiver.getBalance() + transaction.getAmount());

            userRepository.save(sender);
            userRepository.save(receiver);
            
            // ... (rest of your save logic)
            System.out.println("Incentive of " + incentive + " applied!");
        }
    }
}