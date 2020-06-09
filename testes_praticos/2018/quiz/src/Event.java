import java.util.ArrayList;
import java.util.List;

public class Event {
    String title;
    String date;
    String description;
    List<Person> people;

    public Event(String title) {
        this.title = title;
        this.date = "";
        this.description = "";
        this.people = new ArrayList<>();
    }

    public Event(String title, String date) {
        this.title = title;
        this.date = date;
        this.description = "";
        this.people = new ArrayList<>();
    }

    public Event(String title, String date, String description) {
        this.title = title;
        this.date = date;
        this.description = description;
        this.people = new ArrayList<>();
    }

    public Event(Event e) {
        this.title = e.getTitle();
        this.date = e.getDate();
        this.description = e.getDescription();
        this.people = e.getPeople();
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }

    @Override
    public String toString(){
        return title + " is a " + description + " and will be held at " + date + ".";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // are the references equal
        if (o == null) return false; // is the other object null
        if (getClass() != o.getClass()) return false; // both objects the same class
        Event p = (Event) o; // cast the other object
        return title == ((Event) o).getTitle() &&
                description == ((Event) o).getDescription() &&
                date == ((Event) o).getDate(); // actual comparison
    }

    public void addPerson(Person person) {
        for(Person p : people){
            if(p.getName() == person.getName())
                return;
        }
        people.add(person);
    }

    public int getAudienceCount() {
        return people.size();
    }
}
