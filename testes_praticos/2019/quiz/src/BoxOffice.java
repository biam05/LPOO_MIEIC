import java.util.ArrayList;
import java.util.List;

public class BoxOffice {

    public static List<Ticket> buy(Concert concert, int quantity) throws InvalidTicket {
        int beginning = concert.getId();
        int i = concert.getId();
        List<Ticket> tickets_bought = new ArrayList<>();
        while(i < beginning+quantity){
            tickets_bought.add(new Ticket(i,concert));
            i++;
        }
        concert.setId(i);
        return tickets_bought;
    }
}
