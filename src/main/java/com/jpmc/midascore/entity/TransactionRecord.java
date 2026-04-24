package com.jpmc.midascore.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TransactionRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long sender;   // Changed from String to long
    private long receiver; // Changed from String to long
    private float amount;

    public TransactionRecord() {}

    public TransactionRecord(long sender, long receiver, float amount) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }

    // Standard Getters and Setters
    public Long getId() { return id; }
    public long getSender() { return sender; }
    public void setSender(long sender) { this.sender = sender; }
    public long getReceiver() { return receiver; }
    public void setReceiver(long receiver) { this.receiver = receiver; }
    public float getAmount() { return amount; }
    public void setAmount(float amount) { this.amount = amount; }
}