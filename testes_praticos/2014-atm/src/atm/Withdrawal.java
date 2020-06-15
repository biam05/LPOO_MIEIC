package atm;

import java.text.DecimalFormat;

public class Withdrawal extends Transaction{

    protected double amount;

    public Withdrawal(ATM atm, Session session, Card card, int number) {
        super(atm, session, card, number);
        this.amount = 0.00;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString(){
        return "Withdrawal at ATM " + atm.getID() + " (" + atm.getLocation() + ", " + atm.getBank() + ") of " + amount;
    }
}
