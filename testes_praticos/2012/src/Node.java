public abstract class Node{
    protected static int number;
    protected Folder parent;
    protected String name;
    protected int size;


    public Node() {
        this.parent = null;
        this.size = 0;
    }

    public Node(Folder parent, String name) {
        this.parent = parent;
        this.name = name;
        this.size = 0;
    }

    public Node(Folder parent, String name, int size) {
        this.parent = parent;
        this.name = name;
        this.size = size;
    }

    public Folder getParent() {
        return parent;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;                   // are the references equal
        if (o == null) return false;                  // is the other object null
        if (getClass() != o.getClass()) return false; // both objects the same class
        Node p = (Node) o;                          // cast the other object
        return parent == p.getParent() && name == p.getName();        // actual comparison
    }

    public int getNumber() {
        return number;
    }

    public static void resetNumbering(int i) {
        number = i;
    }

    public abstract int getSize();
}
