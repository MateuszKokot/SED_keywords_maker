// TODO rozwiązać problem BZ, która nie czyta polskich znaków. Nie wiem czy jest to problem na wyjściu z JDBC czy raczej problem w BD
// TODO dodać komentarze dokumentacyjne

package sed_keywords_maker;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Load config and data files
        Config.loadConfigFromFile();
        AllWordsContainer.loadFile();

        // Connect with DB
        DBConnection.createConnectionWithDB(Config.isConfigLoaded());

        //Do your job :)
        Kernel.doYourJob(DBConnection.isDBConnected() , AllWordsContainer.isFileLoaded());

    }

}