package atm;

public class ATM {
    protected int ID;
    protected String location;
    protected String bank;

    public ATM(int ID, String location, String bank) {
        this.ID = ID;
        this.location = location;
        this.bank = bank;
    }

    public int getID() {
        return ID;
    }

    public String getLocation() {
        return location;
    }

    public String getBank() {
        return bank;
    }
}
