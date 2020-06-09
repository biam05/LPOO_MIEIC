public class Artist extends Act {

    public Artist(String name, String country) {
        super(name, country);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // are the references equal
        if (o == null) return false; // is the other object null
        if (getClass() != o.getClass()) return false; // both objects the same class
        Artist p = (Artist) o; // cast the other object
        return name == ((Artist) o).getName() &&
                country == ((Artist) o).getCountry(); // actual comparison
    }
}
