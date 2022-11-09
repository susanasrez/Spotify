package model;

public class Artist {

    private String id;
    private String name;
    private Integer popularity;

    public Artist() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPopularity() {
        return popularity;
    }

    public void setIdArtist(String idArtist) {
        this.id = idArtist;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }
}
