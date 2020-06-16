import java.util.ArrayList;
import java.util.Objects;

public class Concert {
    protected String city;
    protected String country;
    protected String date;
    protected ArrayList<Act> acts;
    protected int i;

    public Concert(String city, String country, String date) {
        this.city = city;
        this.country = country;
        this.date = date;
        this.acts = new ArrayList<>();
        this.i = 1;
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

    public ArrayList<Act> getActs() {
        return acts;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public void addAct(Act act) {
        acts.add(act);
    }

    @Override
    public boolean equals(Object o) {
        Concert p = (Concert) o;
        return  city == p.getCity() &&
                country == p.getCountry() &&
                date == p.getDate();
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, country, date);
    }

    public boolean isValid(Ticket ticket) throws InvalidTicket {
        if(ticket.getConcert() == this)
            return true;
        else
            return false;
    }

    public boolean participates(Artist artist) {
        for(Act act : acts){
            if(act instanceof Artist){
                if(act.equals(artist)) return true;
            }
            else{
                if(((Band)act).containsArtist(artist)) return true;
            }
        }

        return false;
    }
}
