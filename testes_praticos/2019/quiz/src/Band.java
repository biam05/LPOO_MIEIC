import java.util.ArrayList;

public class Band extends Act {
    protected ArrayList<Artist> artists;

    public Band(String name, String country) {
        super(name, country);
        this.artists = new ArrayList<>();
    }

    public ArrayList<Artist> getArtists() {
        return artists;
    }

    public void addArtist(Artist artist){
        artists.add(artist);
    }

    public boolean containsArtist(Artist artist){
        return artists.contains(artist);
    }
}
