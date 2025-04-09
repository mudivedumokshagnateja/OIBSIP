package com.atm.service;

import com.atm.model.User;
import com.atm.model.Transaction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {
    private HashMap<String, User> users;
    private HashMap<String, List<Transaction>> transactionHistory;

    public Bank() {
        users = new HashMap<>();
        transactionHistory = new HashMap<>();
        // Initialize with some sample users
        initializeUsers();
    }

    private void initializeUsers() {
        // Add some sample users
        users.put("user1", new User("user1", "1234", 1000.0));
        users.put("user2", new User("user2", "5678", 2000.0));
        
        // Initialize transaction history for users
        transactionHistory.put("user1", new ArrayList<>());
        transactionHistory.put("user2", new ArrayList<>());
    }

    public boolean authenticateUser(String userId, String pin) {
        User user = users.get(userId);
        return user != null && user.getPin().equals(pin);
    }

    public User getUser(String userId) {
        return users.get(userId);
    }

    public void addTransaction(String userId, Transaction transaction) {
        transactionHistory.get(userId).add(transaction);
    }

    public List<Transaction> getTransactionHistory(String userId) {
        return transactionHistory.get(userId);
    }

    public boolean transferMoney(String fromUserId, String toUserId, double amount) {
        User fromUser = users.get(fromUserId);
        User toUser = users.get(toUserId);

        if (fromUser == null || toUser == null || fromUser.getBalance() < amount) {
            return false;
        }

        fromUser.setBalance(fromUser.getBalance() - amount);
        toUser.setBalance(toUser.getBalance() + amount);

        // Record transactions
        addTransaction(fromUserId, new Transaction("TRANSFER_OUT", amount, 
            "Transfer to " + toUserId));
        addTransaction(toUserId, new Transaction("TRANSFER_IN", amount, 
            "Transfer from " + fromUserId));

        return true;
    }
} 