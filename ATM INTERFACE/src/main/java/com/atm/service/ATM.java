package com.atm.service;

import java.util.List;
import java.util.Scanner;
import com.atm.model.User;
import com.atm.model.Transaction;

public class ATM {
    private Bank bank;
    private Scanner scanner;
    private String currentUserId;

    public ATM() {
        bank = new Bank();
        scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("\n=== Welcome to ATM System ===");
            System.out.print("Enter User ID: ");
            String userId = scanner.nextLine();
            System.out.print("Enter PIN: ");
            String pin = scanner.nextLine();

            if (bank.authenticateUser(userId, pin)) {
                currentUserId = userId;
                showMenu();
            } else {
                System.out.println("Invalid credentials. Please try again.");
            }
        }
    }

    private void showMenu() {
        while (true) {
            System.out.println("\n=== ATM Menu ===");
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Transaction History");
            System.out.println("6. Quit");
            System.out.print("Enter your choice: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    withdraw();
                    break;
                case 3:
                    deposit();
                    break;
                case 4:
                    transfer();
                    break;
                case 5:
                    showTransactionHistory();
                    break;
                case 6:
                    System.out.println("Thank you for using our ATM. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void checkBalance() {
        User user = bank.getUser(currentUserId);
        System.out.printf("Current Balance: $%.2f%n", user.getBalance());
    }

    private void withdraw() {
        System.out.print("Enter amount to withdraw: $");
        double amount = Double.parseDouble(scanner.nextLine());
        
        User user = bank.getUser(currentUserId);
        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }
        
        if (user.getBalance() >= amount) {
            user.setBalance(user.getBalance() - amount);
            bank.addTransaction(currentUserId, new Transaction("WITHDRAW", amount, "Cash withdrawal"));
            System.out.printf("Withdrawn $%.2f successfully.%n", amount);
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    private void deposit() {
        System.out.print("Enter amount to deposit: $");
        double amount = Double.parseDouble(scanner.nextLine());
        
        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }
        
        User user = bank.getUser(currentUserId);
        user.setBalance(user.getBalance() + amount);
        bank.addTransaction(currentUserId, new Transaction("DEPOSIT", amount, "Cash deposit"));
        System.out.printf("Deposited $%.2f successfully.%n", amount);
    }

    private void transfer() {
        System.out.print("Enter recipient's User ID: ");
        String recipientId = scanner.nextLine();
        System.out.print("Enter amount to transfer: $");
        double amount = Double.parseDouble(scanner.nextLine());
        
        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }
        
        if (bank.transferMoney(currentUserId, recipientId, amount)) {
            System.out.printf("Transferred $%.2f to %s successfully.%n", amount, recipientId);
        } else {
            System.out.println("Transfer failed. Please check the recipient ID and your balance.");
        }
    }

    private void showTransactionHistory() {
        List<Transaction> history = bank.getTransactionHistory(currentUserId);
        System.out.println("\n=== Transaction History ===");
        if (history.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (Transaction transaction : history) {
                System.out.println(transaction);
            }
        }
    }
} 