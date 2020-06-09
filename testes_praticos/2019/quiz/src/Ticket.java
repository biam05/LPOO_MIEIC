public class Ticket {
    int number;
    Concert concert;

    public Ticket(int number, Concert concert) throws InvalidTicket{
        if(number >= 0) {
            this.number = number;
            this.concert = concert;
        }else {
            throw new InvalidTicket();
        }
    }

    public int getNumber() {
        return number;
    }

    public Concert getConcert() {
        return concert;
    }
}
