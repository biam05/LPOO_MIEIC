public class Ticket {
    protected int number;
    protected Concert concert;

    public Ticket(int number, Concert concert) throws InvalidTicket {
        if(number <= 0) throw new InvalidTicket();
        this.number = number;
        this.concert = concert;
    }

    public int getNumber() {
        return number;
    }

    public Concert getConcert() {
        return concert;
    }
}
