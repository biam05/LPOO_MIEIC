import java.util.ArrayList;
import java.util.List;

public class Party extends Event{
    List<Event> events;

    public Party(String name, String date, String description) {
        super(name, date, description);
        this.events = new ArrayList<>();
    }

    public void addEvent(Event e) {
        events.add(e);
    }


    public int getAudienceCount(){
        int c = people.size();
        for(Event event : events)
            c += event.getAudienceCount();
        return c;
    }
}
