package com.aor.refactoring.example3;

public class Percentage extends Discount {
    private final double percentage;

    public Percentage(double percentage) {
        this.percentage = percentage;
    }

    public double applyDiscount(double price) {
        return price - price * percentage;
    }
}
