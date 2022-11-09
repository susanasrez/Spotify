package model;

import java.util.Map;

public class MapManager {

    private final Map<String, String> artists;

    public MapManager(){
        this.artists = Map.of("Quevedo", "52iwsT98xCoGgiGntTiR7K",
                "Mora","0Q8NcsJwoCbZOHHW63su5S",
                "Lyanno", "1Ts9of7VPZElwPQnqnDSfW",
                "Bad Bunny", "4q3ewBCX7sLwd24euuV69X",
                "JQuiles","14zUHaJZo1mnYtn6IBRaRP",
                "Maluma", "1r4hJ1h58CWwUQe3MxPuau",
                "Juan Magán", "1ackd5XprZEkH3McKbQD51"
        );
    }
    public Map<String, String> getArtists(){
        return artists;
    }
    public String showArtist(){
        return artists.keySet().toString();
    }

    public String idArtist(String name){ return artists.get(name);}

}
