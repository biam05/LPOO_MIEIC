import static java.lang.String.valueOf;

public class Attendee extends Person {
    boolean paid;

    public Attendee(String name) {
        super(name);
        paid = false;
    }

    public Attendee(String name, int age){
        super(name, age);
        paid = false;
    }

    public boolean hasPaid() {
        return paid;
    }

    @Override
    public String toString(){
        return "Attendee " + name + (paid ? " has" : " hasn't") + " paid its registration.";
    }
}
