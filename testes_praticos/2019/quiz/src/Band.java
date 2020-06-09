import java.util.ArrayList;
import java.util.List;

public class Band extends Act {

    List<Artist> artists;

    public Band(String name, String country) {
        super(name, country);
        this.artists = new ArrayList<>();
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void addArtist(Artist artist) {
        artists.add(artist);
    }

    public boolean containsArtist(Artist artist) {
        for(Artist a : artists){
            if(a.equals(artist))
                return true;
        }
        return false;
    }
}
