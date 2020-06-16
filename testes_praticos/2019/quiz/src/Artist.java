public class Artist extends Act {
    public Artist(String name, String country) {
        super(name, country);
    }

    @Override
    public boolean equals(Object o) {
        Artist p = (Artist) o;
        return  name == p.getName() &&
                country == p.getCountry();
    }
}
