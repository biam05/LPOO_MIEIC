import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Concert {
    String city;
    String country;
    String date;
    List<Act> acts;
    int id;

    public Concert(String city, String country, String date) {
        this.city = city;
        this.country = country;
        this.date = date;
        this.acts = new ArrayList<>();
        this.id = 1;
    }

    public void addAct(Act a) {
        acts.add(a);
    }

    public List<Act> getActs() {
        return acts;
    }

    public void setActs(List<Act> acts) {
        this.acts = acts;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // are the references equal
        if (o == null) return false; // is the other object null
        if (getClass() != o.getClass()) return false; // both objects the same class
        Concert p = (Concert) o; // cast the other object
        return city == ((Concert) o).getCity() &&
                date == ((Concert) o).getDate() &&
                country == ((Concert) o).getCountry(); // actual comparison
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, country, date);
    }

    public boolean isValid(Ticket ticket) {
        return ticket.getConcert() == this;
    }

    public boolean participates(Artist artist) {
        List<Act> newActs = new ArrayList<>();
        for(Act act : acts){
            if(act instanceof Artist)
                if(act.equals(artist))
                    continue;
            if(act instanceof Band)
                if(((Band) act).containsArtist(artist))
                    continue;
            newActs.add(act);
        }
        if(newActs.size() == acts.size()) return false;
        else{
            setActs(newActs);
            return true;
        }

    }
}
