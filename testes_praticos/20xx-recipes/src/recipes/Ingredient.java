package recipes;

public abstract class Ingredient implements Comparable<Ingredient> {
    protected String name;

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;                   // are the references equal
        if (o == null) return false;                  // is the other object null
        if (getClass() != o.getClass()) return false; // both objects the same class
        Ingredient p = (Ingredient) o;                          // cast the other object
        return this.name == p.getName();       // actual comparison
    }

    @Override
    public abstract String toString();


    @Override
    public int compareTo(Ingredient i) {
        return name.compareTo(i.getName());
    }
}
