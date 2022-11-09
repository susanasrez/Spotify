package controller.dataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBSelect extends DBManager {

    public DBSelect(){
        super();
    }

    public void selectArtist(String idArtist) throws SQLException {
        String sql = "SELECT * FROM artists WHERE idArtist = ?";
        PreparedStatement s = conn.prepareStatement(sql);
        s.setString(1, idArtist);
        ResultSet rs = s.executeQuery();
        while(rs.next()) {
            System.out.println("Nombre del artista: " + rs.getString("name"));
            System.out.println("Popularidad del artista: " + rs.getInt("popularity"));
        }
    }

    public void selectAlbum(String idArtist) throws SQLException {
        String sql = "SELECT * FROM albums WHERE idArtist = ?";
        PreparedStatement s = conn.prepareStatement(sql);
        s.setString(1, idArtist);
        ResultSet rs = s.executeQuery();
        while(rs.next()) {
            System.out.println("Nombre del album: " + rs.getString("name"));
            System.out.println("Fecha del lanzamiento: " + rs.getString("date"));
            System.out.println("Número de canciones: " + rs.getInt("totaltracks"));
            System.out.println("Url de la imagen: " + rs.getString("urlImage"));
            System.out.println("\n");
        }
    }

    public void selectTracks(String idArtist) throws SQLException {
        String sql = "SELECT * FROM tracks WHERE idArtist = ?";
        PreparedStatement s = conn.prepareStatement(sql);
        s.setString(1, idArtist);
        ResultSet rs = s.executeQuery();
        while(rs.next()) {
            System.out.println("Nombre de la canción: " + rs.getString("name"));
            System.out.println("Duración en segundos: " + rs.getInt("duration"));
            System.out.println("Canción explícita: " + rs.getBoolean("explicit"));
            System.out.println("\n");
        }
    }

}
