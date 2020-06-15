public abstract class Facility {
    protected String name;
    protected int capacity;

    public Facility() {
        this.capacity = 0;
    }

    public Facility(int capacity) {
        this.capacity = capacity;
    }

    public String getName(){ return name;}

    @Override
    public abstract String toString();

    public abstract int getCapacity();

    public abstract boolean canEnter(User u);
}
