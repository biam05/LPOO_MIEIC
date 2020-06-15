package atm;

public class Deposit extends Transaction{

    public Deposit(ATM atm, Session session, Card card, int number) {
        super(atm, session, card, number);
    }
}
