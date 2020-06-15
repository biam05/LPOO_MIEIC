import java.util.ArrayList;

public class User {
    protected String name;
    protected ArrayList<Room> authorizedRooms;

    public User(String name) {
        this.name = name;
        this.authorizedRooms = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;                   // are the references equal
        if (o == null) return false;                  // is the other object null
        if (getClass() != o.getClass()) return false; // both objects the same class
        User p = (User) o;                          // cast the other object
        return name == p.getName();        // actual comparison
    }

    public ArrayList<Room> getAuthorizedRooms() {
        return authorizedRooms;
    }

    public void addAuthorizedRoom(Room r){
        if(!authorizedRooms.contains(r))
            authorizedRooms.add(r);
    }

}
