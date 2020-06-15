package atm;

public abstract class Transaction {
    protected ATM atm;
    protected Session session;
    protected Card card;
    protected int number;

    public Transaction(ATM atm, Session session, Card card, int number) {
        this.atm = atm;
        this.session = session;
        this.card = card;
        this.number = number;
    }
}
