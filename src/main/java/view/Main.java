package view;

import controller.dataBase.DBSelect;
import controller.Controller;
import model.MapManager;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws Exception {

        String clientId;
        String clientSecret;

        Scanner sc = new Scanner(System.in);

        System.out.println("Remember to change your url.");
        System.out.printf("Introduce your client Id: ");
        clientId = sc.next();
        System.out.printf("Introduce your client Secret: ");
        clientSecret = sc.next();

        Controller controller = new Controller(clientId, clientSecret);
        controller.execute();

        MapManager mapManager = new MapManager();
        System.out.println("\nArtists in the database: ");
        System.out.println(mapManager.showArtist());
        System.out.printf("\nArtist:");
        String name = sc.next();
        boolean result = mapManager.getArtists().containsKey(name);
        if(result == true){
            System.out.println("Selected artist's information:");
            String idArtist = mapManager.idArtist(name);
            DBSelect dbSelect = new DBSelect();
            System.out.println("Artist's information:");
            dbSelect.selectArtist(idArtist);
            System.out.println("Artist's albums:");
            dbSelect.selectAlbum(idArtist);
            System.out.println("Artist's tracks:");
            dbSelect.selectTracks(idArtist);
        }
        System.out.println("Proceso finalizado.");

    }

}
