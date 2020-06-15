package atm;

import java.util.ArrayList;

public class Session implements Countable{
    protected ATM atm;
    protected ArrayList<Transaction> transactions;

    public Session(ATM atm) {
        this.atm = atm;
        this.transactions = new ArrayList<>();
    }

    public ATM getATM() {
        return atm;
    }

    public void addTransaction(Transaction t) {
        if(!transactions.contains(t))
            transactions.add(t);
    }

    @Override
    public int count() {
        return transactions.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;                   // are the references equal
        if (o == null) return false;                  // is the other object null
        if (getClass() != o.getClass()) return false; // both objects the same class
        Session p = (Session) o;                          // cast the other object
        return atm.getID() == p.getATM().getID();        // actual comparison
    }
}
