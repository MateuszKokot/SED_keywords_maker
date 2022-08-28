package sed_keywords_maker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class is tool to create connection with database.
 */
public class DBConnection {

    private static String DBURL; //   "jdbc:mysql://127.0.0.1:3306/szukamekipydo.pl?useUnicode=yes&characterEncoding=UTF8"
    private static String DBUSER;
    private static String DBPASS;
    public static Connection connection;
    public static Statement statement;
    public static boolean DBConnected = false;

    /**
     * This method created connection with database if config is loaded.
     * @param isConfigLoaded Put in Config.isConfigLoaded(). If config is loaded it returns TRUE.
     */
    public static void createConnectionWithDB(boolean isConfigLoaded) {

        if (isConfigLoaded) {
            DBURL = "jdbc:mysql://" + Config.getHost() + "/" + Config.getDatabase() + "?allowMultiQueries=true"; // TODO Dodaj to ?useUnicode=yes&characterEncoding=UTF8
            DBUSER = Config.getUserName();
            DBPASS = Config.getPassword();
            try {
                connection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
                statement = connection.createStatement();
                setDBConnected(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method release resources needed to create connection with database.
     */
    public static void releaseResource (){
        if (DBConnection.statement != null){
            try {
                DBConnection.statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (DBConnection.connection != null){
            try {
                DBConnection.connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Getters and Setters
     * @param DBConnected return true if database is connected correctly.
     */
    private static void setDBConnected(boolean DBConnected) {
        DBConnection.DBConnected = DBConnected;
    }
    public static boolean isDBConnected() {
        return DBConnected;
    }


}
