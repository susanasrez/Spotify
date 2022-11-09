package controller.dataBase;

import model.Album;
import model.Artist;
import model.Track;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBInsert extends DBManager {

    public DBInsert(){
        super();
    }

    public void insertArtist(Artist artist) {
        String sql = "INSERT INTO Artists(idArtist,name,popularity) VALUES(?,?,?)";
        try (PreparedStatement pstm = conn.prepareStatement(sql)){
            pstm.setString(1, artist.getId());
            pstm.setString(2, artist.getName());
            pstm.setInt(3, artist.getPopularity());
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertAlbum(Album album) {
        String sql = "INSERT INTO Albums(idAlbum,name,date,totalTracks,urlImage, idArtist) VALUES(?,?,?,?,?,?)";

        try (PreparedStatement pstmtAlbum = conn.prepareStatement(sql)) {
            pstmtAlbum.setString(1, album.getId());
            pstmtAlbum.setString(2, album.getName());
            pstmtAlbum.setString(3, album.getDate());
            pstmtAlbum.setInt(4, album.getTotalTracks());
            pstmtAlbum.setString(5, album.getUrlImage());
            pstmtAlbum.setString(6, album.getIdArtist());
            pstmtAlbum.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertTrack(Track track) {
        String sql = "INSERT INTO Tracks(idTrack,name,isLocal,duration,explicit, idAlbum, idArtist) VALUES(?,?,?,?,?,?,?)";

        try (PreparedStatement pstmtTrack = conn.prepareStatement(sql)) {
            pstmtTrack.setString(1, track.getId());
            pstmtTrack.setString(2, track.getName());
            pstmtTrack.setBoolean(3, track.getIsLocal());
            pstmtTrack.setInt(4, track.getDuration());
            pstmtTrack.setBoolean(5, track.getExplicit());
            pstmtTrack.setString(6, track.getIdAlbum());
            pstmtTrack.setString(7, track.getIdArtist());
            pstmtTrack.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
