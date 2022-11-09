package controller;

import controller.accessor.SpotifyAccessor;
import controller.dataBase.DBCreate;
import controller.dataBase.DBInsert;
import controller.dataBase.DBManager;
import model.MapManager;

public class Controller {

    private String clientId;
    private String clientSecret;

    public Controller(String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public void execute() throws Exception {
        DBCreate create = new DBCreate();
        create.setConnection();
        DBInsert dbInsert = new DBInsert();
        MapManager artists = new MapManager();
        SpotifyAccessor accessor = new SpotifyAccessor(clientId, clientSecret);
        GetApi getApi = new GetApi();
        getApi.getExecute(artists, dbInsert, accessor);
        create.setClose();

    }
}
