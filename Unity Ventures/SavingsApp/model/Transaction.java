package model;

import java.time.LocalDate;

public class Transaction {
    public enum Type {
        INCOME, EXPENSE, REWARD, COUPON
    }

    private String id;
    private LocalDate date;
    private double amount;
    private String category;
    private String description;
    private Type type;
    private boolean isUnwanted;

    public Transaction(String id, LocalDate date, double amount, String category, String description, Type type, boolean isUnwanted) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.category = category;
        this.description = description;
        this.type = type;
        this.isUnwanted = isUnwanted && type == Type.EXPENSE; // Only expenses can be unwanted
    }

    public String getId() { return id; }
    public LocalDate getDate() { return date; }
    public double getAmount() { return amount; }
    public String getCategory() { return category; }
    public String getDescription() { return description; }
    public Type getType() { return type; }
    public boolean isUnwanted() { return isUnwanted; }

    @Override
    public String toString() {
        return "Transaction{" +
                "date=" + date +
                ", amount=" + amount +
                ", category='" + category + '\'' +
                ", type=" + type +
                '}';
    }
}
