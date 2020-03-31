package com.aor.refactoring.example3;

public class Fixed extends Discount {
    private final int fixed;

    public Fixed(int fixed) {
        this.fixed = fixed;
    }

    public double applyDiscount(double price) {
        return fixed > price ? 0 : price - fixed;
    }
}
