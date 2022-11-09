package controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import controller.accessor.SpotifyAccessor;
import controller.dataBase.DBInsert;
import model.Album;
import model.Artist;
import model.MapManager;
import model.Track;

import java.util.Map;

public class GetApi {

    public void getExecute(MapManager mapManager, DBInsert dbInsert , SpotifyAccessor accessor) throws Exception {
        for(String idArtist: mapManager.getArtists().values()){
            getArtistAlbums(dbInsert,accessor, idArtist);
        }
    }

    public Artist getArtists(String idArtist, SpotifyAccessor accessor) throws Exception {
        String jsonArtist = accessor.get("/artists/" + idArtist, Map.of());
        JsonObject jsonObject = new Gson().fromJson(jsonArtist, JsonObject.class);
        Artist artist = new Artist();
        artist.setIdArtist(idArtist);
        artist.setName(jsonObject.get("name").getAsString());
        artist.setPopularity(jsonObject.get("popularity").getAsInt());
        return artist;
    }
    public void getAlbum(JsonArray albums, DBInsert database, String idArtist) {
        for (JsonElement item: albums){
            Album album = new Album();
            album.setIdAlbum(item.getAsJsonObject().get("id").getAsString());
            album.setName(item.getAsJsonObject().get("name").getAsString());
            album.setDate(item.getAsJsonObject().get("release_date").getAsString());
            album.setTotalTracks(item.getAsJsonObject().get("total_tracks").getAsInt());
            JsonArray urls = item.getAsJsonObject().get("images").getAsJsonArray();
            album.setUrlImage(urls.get(0).getAsJsonObject().get("url").getAsString());
            album.setIdArtist(idArtist);
            database.insertAlbum(album);
        }
    }

    public void getTracks(JsonArray tracks, DBInsert database, String idAlbum, String idArtist) {
        for(JsonElement item: tracks){
            Track track = new Track();
            track.setIdTrack(item.getAsJsonObject().get("id").getAsString());
            track.setName(item.getAsJsonObject().get("name").getAsString());
            track.setIsLocal(item.getAsJsonObject().get("is_local").getAsBoolean());
            track.setDuration(item.getAsJsonObject().get("duration_ms").getAsInt() / 1000);
            track.setExplicit(item.getAsJsonObject().get("explicit").getAsBoolean());
            track.setIdAlbum(idAlbum);
            track.setIdArtist(idArtist);
            database.insertTrack(track);
        }
    }

    public void getArtistAlbums(DBInsert dataBaseInsert, SpotifyAccessor accessor, String idArtist) throws Exception {
        Artist artist = getArtists(idArtist, accessor);
        dataBaseInsert.insertArtist(artist);
        String response = accessor.get("/artists/" + idArtist + "/albums", Map.of());
        JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
        JsonArray albums = jsonObject.get("items").getAsJsonArray();
        getAlbum(albums, dataBaseInsert, idArtist);

        for (JsonElement album: albums){
            getAlbumTracks(dataBaseInsert, accessor, idArtist, album);
        }
    }

    public void getAlbumTracks(DBInsert dataBaseInsert, SpotifyAccessor accessor, String idArtist, JsonElement album) throws Exception {
        String idAlbum = album.getAsJsonObject().get("id").getAsString();
        String responseTracks = accessor.get("/albums/" + idAlbum + "/tracks", Map.of());
        JsonObject jsonObjectTracks = new Gson().fromJson(responseTracks, JsonObject.class);
        JsonArray tracks = jsonObjectTracks.get("items").getAsJsonArray();
        getTracks(tracks, dataBaseInsert, idAlbum, idArtist);
    }
}
