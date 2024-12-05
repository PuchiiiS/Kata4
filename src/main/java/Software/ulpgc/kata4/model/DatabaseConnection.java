package Software.ulpgc.kata4.model;

import java.net.URI;
import java.net.URISyntaxException;

public class DatabaseConnection {
    private static final String DATABASE_URL = "jdbc:sqlite:" + getDatabasePath();

    private static String getDatabasePath() {
        URI dbUri;
        try{
            dbUri = DatabaseConnection.class.getClassLoader().getResource("movies.db").toURI();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e){
            throw new RuntimeException("La base de datos no se encuentra o no existe");
        }
        return dbUri.getPath();
    }
}