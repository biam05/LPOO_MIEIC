import java.util.ArrayList;
import java.util.List;

public class BoxOffice {
    public static List<Ticket> buy(Concert concert, int i) throws InvalidTicket {
        List<Ticket> tickets = new ArrayList<>();
        for(int j = 0; j < i; j++){
            tickets.add(new Ticket(concert.getI(), concert));
            concert.setI(concert.getI()+1);
        }
        return tickets;
    }
}
