package model;

public class Track {

    private String id;
    private String name;
    private Boolean isLocal;
    private Integer duration;
    private Boolean explicit;
    private String idAlbum;
    private String idArtist;

    public Track(){
    }

    public void setIdTrack(String idTrack) {
        this.id = idTrack;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIsLocal(Boolean isLocal) {
        this.isLocal = isLocal;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public void setExplicit(Boolean explicit) {
        this.explicit = explicit;
    }

    public void setIdAlbum(String idAlbum) {
        this.idAlbum = idAlbum;
    }

    public void setIdArtist(String idArtist){this.idArtist = idArtist;}

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean getIsLocal() {
        return isLocal;
    }

    public Integer getDuration() {
        return duration;
    }

    public Boolean getExplicit() {
        return explicit;
    }

    public String getIdAlbum() {
        return idAlbum;
    }

    public String getIdArtist(){return idArtist;}
}
