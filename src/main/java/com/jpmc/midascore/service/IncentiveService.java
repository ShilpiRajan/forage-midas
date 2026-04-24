package com.jpmc.midascore.service;

import com.jpmc.midascore.foundation.Transaction;
import org.springframework.stereotype.Service;

@Service
public class IncentiveService {
    
    public float calculateIncentive(Transaction transaction) {
        // Logic: 10% bonus for now? Or just a flat rate. 
        // Let's go with a flat 5% incentive on the amount.
        return transaction.getAmount() * 0.05f;
    }
}