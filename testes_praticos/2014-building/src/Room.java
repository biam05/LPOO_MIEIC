public class Room extends Facility{
    protected Building building;
    protected String number;
    protected int floor;

    public Room(Building building, String number, int floor) throws Exception {
        super();
        if(floor > building.maxFloor) throw new IllegalArgumentException();
        this.building = building;
        this.number = number;
        this.floor = floor;
        setName();
        if(duplicate(building, number, floor)) throw new DuplicateRoomException();
        this.building.addRoom(this);
    }

    public Room(Building building, String number, int floor, int capacity) throws Exception {
        super(capacity);
        if(floor > building.maxFloor) throw new IllegalArgumentException();
        this.building = building;
        this.number = number;
        this.floor = floor;
        setName();
        if(duplicate(building, number, floor)) throw new DuplicateRoomException();
        this.building.addRoom(this);
    }

    public boolean duplicate(Building b, String string, int i) {

        for (int k = 0; k < b.getRooms().size(); k++) {
            if (b.getRooms().get(k).getNumber() == string && b.getRooms().get(k).getFloor() == i)
                return true;
            else
                return false;
        }
        return false;
    }

    public Building getBuilding() {
        return building;
    }

    public String getNumber() {
        return number;
    }

    public int getFloor() {
        return floor;
    }

    public void setName(){
        this.name = this.building.getName() + this.number;
    }

    public int getCapacity(){
        return this.capacity;
    }

    @Override
    public String toString() {
        return "Room(" + this.building.getName() + "," + this.number + ")";
    }

    public void authorize(User u) {
        u.addAuthorizedRoom(this);
    }

    public boolean canEnter(User u){
        if(u.getAuthorizedRooms().contains(this))
            return true;
        return false;
    }
}
