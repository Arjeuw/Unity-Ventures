package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SavingsManager {
    private List<Transaction> transactions;

    public SavingsManager() {
        this.transactions = new ArrayList<>();
        // Add some dummy data for demonstration
        addTransaction(LocalDate.now().minusDays(2), 5000.0, "Salary", "Monthly Salary", Transaction.Type.INCOME, false);
        addTransaction(LocalDate.now().minusDays(1), 150.0, "Groceries", "Supermarket", Transaction.Type.EXPENSE, false);
        addTransaction(LocalDate.now(), 200.0, "Gaming", "Video Game", Transaction.Type.EXPENSE, true); // Unwanted
        addTransaction(LocalDate.now(), 50.0, "Cashback", "Credit Card Reward", Transaction.Type.REWARD, false);
    }

    public void addTransaction(LocalDate date, double amount, String category, String description, Transaction.Type type, boolean isUnwanted) {
        Transaction t = new Transaction(UUID.randomUUID().toString(), date, amount, category, description, type, isUnwanted);
        transactions.add(t);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
    
    public double getTotalBalance() {
        double balance = 0;
        for (Transaction t : transactions) {
            if (t.getType() == Transaction.Type.INCOME || t.getType() == Transaction.Type.REWARD) {
                balance += t.getAmount();
            } else if (t.getType() == Transaction.Type.EXPENSE) {
                balance -= t.getAmount();
            }
        }
        return balance;
    }

    public double getTotalExpenses() {
        double total = 0;
        for (Transaction t : transactions) {
            if (t.getType() == Transaction.Type.EXPENSE) {
                total += t.getAmount();
            }
        }
        return total;
    }

    public double getUnwantedExpenses() {
        double total = 0;
        for (Transaction t : transactions) {
            if (t.getType() == Transaction.Type.EXPENSE && t.isUnwanted()) {
                total += t.getAmount();
            }
        }
        return total;
    }
    
    public double getTotalRewards() {
        double total = 0;
        for (Transaction t : transactions) {
            if (t.getType() == Transaction.Type.REWARD) {
                total += t.getAmount();
            }
        }
        return total;
    }
}
