package model;

public class Album {

    private String id;
    private String name;
    private String date;
    private Integer totalTracks;
    private String urlImage;
    private String idArtist;

    public Album(){
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public Integer getTotalTracks() {
        return totalTracks;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public String getIdArtist() {
        return idArtist;
    }

    public void setIdAlbum(String idAlbum) {
        this.id = idAlbum;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTotalTracks(Integer totalTracks) {
        this.totalTracks = totalTracks;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public void setIdArtist(String idArtist) {
        this.idArtist = idArtist;
    }
}
