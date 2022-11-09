package controller.dataBase;

public class DBCreate extends DBManager {
    public DBCreate() {
        super();
    }

    public void setConnection() throws Exception {
        statement = conn.createStatement();
        statement.execute("DROP TABLE IF EXISTS artists;");
        statement.execute("DROP TABLE IF EXISTS albums;");
        statement.execute("DROP TABLE IF EXISTS tracks;");

        statement.execute("CREATE TABLE IF NOT EXISTS artists (" +
                "idArtist TEXT PRIMARY KEY, " +
                "name TEXT NOT NULL, " +
                "popularity INTEGER" +
                ")");
        statement.execute("CREATE TABLE IF NOT EXISTS albums (" +
                "idAlbum TEXT PRIMARY KEY," +
                "name TEXT NOT NULL, " +
                "date TEXT," +
                "totalTracks INTEGER," +
                "urlImage TEXT,"+
                "idArtist TEXT,"+
                "FOREIGN KEY (idArtist) REFERENCES artists(idArtist)"+
                ")");
        statement.execute("CREATE TABLE IF NOT EXISTS tracks (" +
                "idTrack TEXT PRIMARY KEY," +
                "name TEXT, " +
                "isLocal BOOLEAN," +
                "duration INTEGER," +
                "explicit BOOLEAN,"+
                "idAlbum TEXT,"+
                "idArtist TEXT,"+
                "FOREIGN KEY (idAlbum) REFERENCES albums(idAlbum),"+
                "FOREIGN KEY (idArtist) REFERENCES artists(idArtist)"+
                ")");
    }

}
