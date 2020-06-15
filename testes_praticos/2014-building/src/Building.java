import java.util.ArrayList;

public class Building extends Facility{
    protected int minFloor;
    protected int maxFloor;
    protected ArrayList<Room> rooms;

    public Building(String name, int minFloor, int maxFloor) throws IllegalArgumentException{
        super();
        if(maxFloor < minFloor) throw new IllegalArgumentException();
        this.name = name;
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
        this.rooms = new ArrayList<>();
    }

    public int getMinFloor() {
        return minFloor;
    }

    public int getMaxFloor() {
        return maxFloor;
    }

    @Override
    public String toString() {
        return "Building(" + this.name + ")";
    }

    public int getCapacity(){
        for(Room room : rooms){
            this.capacity+=room.getCapacity();
        }
        return this.capacity;
    }

    @Override
    public boolean canEnter(User u) {
        for(Room room : rooms){
            if(room.canEnter(u))
                return true;
        }
        return false;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void addRoom(Room r) throws DuplicateRoomException{
        if(!rooms.contains(r))
            rooms.add(r);
        else
            throw new DuplicateRoomException();
    }
}
